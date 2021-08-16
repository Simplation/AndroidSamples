package com.simplation.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.simplation.service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    class MyServiceConnection : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                startService(it)
            }
        }

        binding.stopService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                stopService(it)
            }
        }

        binding.bindService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                val serviceConnection = MyServiceConnection()
                bindService(it, serviceConnection, Context.BIND_AUTO_CREATE)
            }
        }

        binding.unbindService.setOnClickListener {
            val serviceConnection = MyServiceConnection()
            unbindService(serviceConnection)
        }

    }
}