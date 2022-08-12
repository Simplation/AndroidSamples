package com.simplation.localstorage.all

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplation.localstorage.databinding.ActivityCpBinding

class CPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCpBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}