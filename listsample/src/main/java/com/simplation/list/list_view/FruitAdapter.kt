package com.simplation.list.list_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.simplation.list.Fruit
import com.simplation.list.R

/**
 * @作者: Simplation
 * @日期: 2021/08/16 22:38
 * @描述:
 * @更新:
 */
class FruitAdapter(context: Context, resource: Int, objects: MutableList<Fruit>) :
    ArrayAdapter<Fruit>(context, resource, objects) {
    var resourceId: Int = 0
    private var data = mutableListOf<Fruit>()

    init {
        resourceId = resource
        data = objects
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val fruit = data[position]

        val view: View =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false)

        val fruitImage = view.findViewById(R.id.fruit_image) as ImageView
        val fruitName = view.findViewById(R.id.fruit_name) as TextView
        fruitImage.setImageResource(fruit.imageId)
        fruitName.text = fruit.name

        return view
    }
}