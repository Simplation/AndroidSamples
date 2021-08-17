package com.simplation.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplation.list.databinding.ActivityMainBinding
import com.simplation.list.list_view.ListViewActivity
import com.simplation.list.recycler_view.RecyclerViewActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listViewButton.setOnClickListener {
            Intent(this@MainActivity, ListViewActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.recyclerViewButton.setOnClickListener {
            Intent(this@MainActivity, RecyclerViewActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}