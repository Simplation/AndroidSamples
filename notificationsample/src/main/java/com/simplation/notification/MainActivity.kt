package com.simplation.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.simplation.notification.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendNotice.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, NotificationActivity::class.java)

            val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val notificationChannel = NotificationChannel("default", "default", NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)

            notificationChannel.lightColor = Color.RED
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            notificationChannel.setShowBadge(true)
            notificationChannel.setBypassDnd(true)
            // notificationChannel.setVibrationPattern(longArrayOf(){ 100, 200, 300, 400 });
            notificationChannel.description = "channel";

            manager.createNotificationChannel(notificationChannel);
            // 取消通知栏图标
            // manager.cancel(1)

            val notification = NotificationCompat.Builder(this, "default")
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setContentText("Learn aaijfl;fda;dkf;alsdkf;asldmvlkn;kldvalmf;vlmf;lv;'ldmfvm;")
                .setContentIntent(pendingIntent)
                .setSound(Uri.fromFile(File("/system/media/audio/ringtones/Luna.ogg")))
                .setAutoCancel(true)
                //.setStyle(NotificationCompat.BigTextStyle().bigText("asdkfksdljflaksnvlksdnvsdnvkjsnvknkfjnavknvknfkvjnfsk"))
                //.setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)))
                .setPriority(NotificationCompat.PRIORITY_MAX)   // 设置优先级
                .build()

            manager.notify(1, notification)
        }
    }
}