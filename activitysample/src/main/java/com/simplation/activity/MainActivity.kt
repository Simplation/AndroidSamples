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
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }

        binding.startDialogActivity.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }

        binding.startAnotherActivity.setOnClickListener {
            // AnotherActivity.actionStart(this, intent)
        }

        binding.showDialog.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("This is dialog")
            dialog.setMessage("Something is important.")
            dialog.setPositiveButton(
                "OK"
            ) { _, _ ->
                Toast.makeText(
                    this,
                    "Ok...",
                    Toast.LENGTH_SHORT
                ).show()
            }
            dialog.setNegativeButton(
                "Cancel"
            ) { _, _ ->
                Toast.makeText(
                    this,
                    "Cancel...",
                    Toast.LENGTH_SHORT
                ).show()
            }
            dialog.show()
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