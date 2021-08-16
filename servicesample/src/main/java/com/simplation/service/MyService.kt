package com.simplation.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * @作者: Simplation
 * @日期: 2021/08/16 21:34
 * @描述:
 * @更新:
 */
class MyService : Service() {
    private val TAG = MyService::class.java.simpleName
    private val mBinder = DownloadBinder()

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    // 创建服务的时候调用
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate executed")
    }

    // 服务启动的时候调用
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand executed")
        return super.onStartCommand(intent, flags, startId)
    }

    // 服务销毁的时候调用
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy executed")
    }

    class DownloadBinder : Binder() {
        fun startDownload() {
            Log.d("MyService", "startDownload executed")
        }

        fun getProgress(): Int {
            Log.d("MyService", "getProgress executed")
            return 0
        }
    }
}
