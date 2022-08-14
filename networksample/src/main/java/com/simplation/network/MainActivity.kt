package com.simplation.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplation.network.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendRequest.setOnClickListener {
            // sendRequestWithHttpURLConnection()

            sendRequestWithOkHttp()
        }
    }

    private fun sendRequestWithOkHttp() {
        Thread {
            try {
                val okHttpClient = OkHttpClient()
                val request = Request.Builder()
                    .url("http://www.baidu.com")
                    .build()

                val response = okHttpClient.newCall(request).execute()
                val responseData = response.body().string()
                showResponse(responseData)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun sendRequestWithHttpURLConnection() = Thread {
        val connection: HttpURLConnection?
        val reader: BufferedReader?

        try {
            val url = URL("http://www.baidu.com")
            connection = url.openConnection() as HttpURLConnection
            connection.apply {
                requestMethod = "GET"
                connectTimeout = 8000
                readTimeout = 8000
            }

            val ins: InputStream = connection.inputStream

            // 对获取到的输入流进行读取
            reader = BufferedReader(InputStreamReader(ins))
            val response = StringBuilder()

            val line: String? = null

            if ((reader.readLine()) != null) {
                response.append(line)
            }

            showResponse(response.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.start()

    private fun showResponse(string: String) {
        runOnUiThread {
            binding.responseText.text = string
        }
    }
}