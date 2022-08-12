package com.simplation.localstorage.all

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplation.localstorage.R
import com.simplation.localstorage.databinding.ActivitySpBinding

class SPActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}