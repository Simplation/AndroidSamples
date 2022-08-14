package com.simplation.localstorage.all

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplation.localstorage.databinding.ActivityFileBinding
import java.io.FileNotFoundException
import java.io.IOException

class FileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = intent.getStringExtra("title")

        binding.btnSave.setOnClickListener {
            // 将数据写入文件
            writeFiles(binding.etContent.text.toString())
            // 将读取到的数据显示在 TextView 中
            binding.tvShowContent.text = readFiles()
        }
    }

    // 将内容写入文件
    private fun writeFiles(content: String) {
        try {
            // 调用 Context 的 openFileOutput() 函数，填入文件名和操作模式，它会返回一个 FileOutputStream 对象。
            val fos = openFileOutput("a.txt", MODE_PRIVATE)
            // 通过 FileOutputStream 对象的 write() 函数写入数据。
            fos.write(content.toByteArray())
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // 读取文件内容
    @Suppress("DEPRECATED_IDENTITY_EQUALS")
    private fun readFiles(): String? {
        var content: String? = null
        try {
            // 调用 openFileInput( )，参数中填入文件名，会返回一个 FileInputStream 对象。
            val fis = openFileInput("a.txt")
            val sb = StringBuffer()
            val buffer = ByteArray(1024)

            var len: Int
            // 使用流对象的 read()方法读取字节
            while (fis.read(buffer).also { len = it } !== -1) {
                sb.append(String(buffer, 0, len))
            }
            content = sb.toString()
            // 调用流的 close() 方法关闭流
            fis.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return content
    }
}