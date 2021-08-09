package com.simplation.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.simplation.broadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var networkChangeReceiver: NetworkChangeReceiver
    private lateinit var intentFilter: IntentFilter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        networkChangeReceiver = NetworkChangeReceiver()
        registerReceiver(networkChangeReceiver, intentFilter)

        binding.button.setOnClickListener {
            Log.d("--- TAG ---", "onCreate: click button")
            val intent = Intent("com.simplation.broadcast.MY_BROADCAST")
            sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }

    class NetworkChangeReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT < 23) {
                val networkInfo = connectivityManager.activeNetworkInfo
                if (networkInfo != null && networkInfo.isAvailable) {
                    Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show()
                    if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                        Toast.makeText(context, "WIFI", Toast.LENGTH_SHORT).show()
                    } else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                        Toast.makeText(context, "移动数据...", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show()
                }
            } else {
                val network = connectivityManager.activeNetwork
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                if (network != null && networkCapabilities != null) {
                    Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show()
                    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Toast.makeText(context, "WIFI", Toast.LENGTH_SHORT).show()
                    } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Toast.makeText(context, "移动数据...", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
