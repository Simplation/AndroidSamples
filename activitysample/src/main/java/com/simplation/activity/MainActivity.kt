package com.simplation.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.simplation.activity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")

        // 取数据
        if (savedInstanceState != null) {
            val tempData = savedInstanceState.getString("data_key")
            Log.d(tag, "tempData is $tempData")
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startNormalActivity.setOnClickListener {
            Intent(this, NormalActivity::class.java).run {
                startActivity(this)
            }
        }

        binding.startDialogActivity.setOnClickListener {
            Intent(this, DialogActivity::class.java).run {
                startActivity(this)
            }
        }

        binding.startAnotherActivity.setOnClickListener {
            AnotherActivity.actionStart(this, "data1", "data2")
        }

        binding.showDialog.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("This is dialog")
                    .setMessage("Something is important.")
                    .setPositiveButton("OK") { _, _ ->
                        Toast.makeText(
                            this@MainActivity,
                            "Ok...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setNegativeButton("Cancel") { _, _ ->
                        Toast.makeText(
                            this@MainActivity,
                            "Cancel...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }.show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 保存数据
        val tempData = "Something you just typed."
        outState.putString("data_key", tempData)
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }
}