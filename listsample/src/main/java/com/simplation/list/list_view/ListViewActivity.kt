package com.simplation.list.list_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.simplation.list.Fruit
import com.simplation.list.R
import com.simplation.list.databinding.ActivityListViewBinding

class ListViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListViewBinding

    /*private val data = arrayOf("Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
            "Strawberry", "Cherry", "Mango", "Orange", "Watermelon", "Pear", "Banana", "Orange",
            "Watermelon", "Pear", "Grape", "Strawberry", "Cherry", "Mango", "Orange", "Watermelon", "Pear")*/

    private var fruitList: MutableList<Fruit> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFruits()

        val fruitAdapter = FruitAdapter(this, R.layout.fruit_item, fruitList)
        binding.listView.adapter = fruitAdapter
        binding.listView.setOnItemClickListener { _: AdapterView<*>, _: View, i: Int, _: Long ->
            val fruit = fruitList[i]
            Toast.makeText(this, fruit.name, Toast.LENGTH_SHORT).show()
        }
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