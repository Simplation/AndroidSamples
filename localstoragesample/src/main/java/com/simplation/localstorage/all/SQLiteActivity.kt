package com.simplation.localstorage.all

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplation.localstorage.databinding.ActivitySqliteBinding

class SQLiteActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySqliteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySqliteBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}