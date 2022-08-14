package com.simplation.audio

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.simplation.audio.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mediaPlayer: MediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        } else {
            initMediaPlayer()  // 本地音频播放

            initVideoView()  // 本地视频播放

            initClick()
        }
    }

    private fun initVideoView() {
        val file = File(getExternalFilesDir(null), "播放文件名称+后缀")
        binding.videoView.setVideoPath(file.path)
    }

    private fun initMediaPlayer() {
        try {
            // val file = File(Environment.getExternalStorageDirectory(), "sound_2.wav")
            val file = File(getExternalFilesDir(null), "sound_2.wav")
            // 指定路径
            mediaPlayer.setDataSource(file.path)
            // 进入准备状态
            mediaPlayer.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initMediaPlayer()
            } else {
                Toast.makeText(this, "You defined permission.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun initClick() {
        binding.playButton.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        binding.pauseButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }

        binding.stopButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.reset()
                initMediaPlayer()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer.apply {
            stop()
            release()
        }
    }
}