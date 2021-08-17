package com.simplation.list.recycler_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplation.list.Fruit
import com.simplation.list.R
import com.simplation.list.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    var fruitList: MutableList<Fruit> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFruits()

        val adapter = FruitRecyclerAdapter(fruitList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        adapter.setOnItemClick(object : FruitRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val fruit = fruitList[position]

                Toast.makeText(
                    this@RecyclerViewActivity,
                    "You clicked view ${fruit.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun initFruits() {
        for (i in 0..1) {
            val apple = Fruit("Apple", R.mipmap.ic_launcher)
            fruitList.add(apple)

            val banana = Fruit("Banana", R.mipmap.ic_launcher)
            fruitList.add(banana)

            val orange = Fruit("Orange", R.mipmap.ic_launcher)
            fruitList.add(orange)

            val watermelon = Fruit("Watermelon", R.mipmap.ic_launcher)
            fruitList.add(watermelon)

            val pear = Fruit("Pear", R.mipmap.ic_launcher)
            fruitList.add(pear)

            val grape = Fruit("Grape", R.mipmap.ic_launcher)
            fruitList.add(grape)

            val strawberry = Fruit("Strawberry", R.mipmap.ic_launcher)
            fruitList.add(strawberry)

            val cherry = Fruit("Cherry", R.mipmap.ic_launcher)
            fruitList.add(cherry)

            val mango = Fruit("Mango", R.mipmap.ic_launcher)
            fruitList.add(mango)
        }
    }
}