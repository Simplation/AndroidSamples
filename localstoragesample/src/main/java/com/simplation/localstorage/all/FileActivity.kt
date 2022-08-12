package com.simplation.localstorage.all

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplation.localstorage.databinding.ActivityFileBinding

class FileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFileBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}