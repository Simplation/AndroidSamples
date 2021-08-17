package com.simplation.list.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simplation.list.Fruit
import com.simplation.list.databinding.FruitRecyclerItemBinding

/**
 * @作者: Simplation
 * @日期: 2021/08/16 23:09
 * @描述:
 * @更新:
 */
class FruitRecyclerAdapter(var mFruitList: MutableList<Fruit>) :
    RecyclerView.Adapter<FruitRecyclerAdapter.FruitViewHolder>() {

    private lateinit var onClick: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val binding =
            FruitRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        /*binding.root.setOnClickListener {
               val position = FruitView.adapterPosition
               val fruit = mFruitList[position]
               Toast.makeText(parent.context, "You clicked view ${fruit.name}", Toast.LENGTH_SHORT).show()
           }*/

        return FruitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val fruit = mFruitList[position]
        holder.bind(fruit)
    }

    override fun getItemCount(): Int {
        return mFruitList.size
    }

    class FruitViewHolder(var itemBinding: FruitRecyclerItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(fruit: Fruit) {
            itemBinding.fruitImage.setImageResource(fruit.imageId)
            itemBinding.fruitName.text = fruit.name
        }
    }

    fun setOnItemClick(onItemClickListener: OnItemClickListener) {
        this.onClick = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}