package com.simplation.localstorage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.simplation.localstorage.all.CPActivity
import com.simplation.localstorage.all.FileActivity
import com.simplation.localstorage.all.SPActivity
import com.simplation.localstorage.all.SQLiteActivity
import com.simplation.localstorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            // SharedPreferences
            btnSp.setOnClickListener {
                Intent(this@MainActivity, SPActivity::class.java).run {
                    putExtra("title", "SharedPreferences 存储示例")
                    startActivity(this)
                }
            }

            // file
            btnFile.setOnClickListener {
                Intent(this@MainActivity, FileActivity::class.java).run {
                    putExtra("title", "File 存储示例")
                    startActivity(this)
                }
            }

            // SQLite
            btnSqlite.setOnClickListener {
                Intent(this@MainActivity, SQLiteActivity::class.java).run {
                    putExtra("title", "SQLite 存储示例")
                    startActivity(this)
                }
            }

            // ContentProvider
            btnCp.setOnClickListener {
                Intent(this@MainActivity, CPActivity::class.java).run {
                    putExtra("title", "ContentProvider 存储示例")
                    startActivity(this)
                }
            }
        }
    }
}