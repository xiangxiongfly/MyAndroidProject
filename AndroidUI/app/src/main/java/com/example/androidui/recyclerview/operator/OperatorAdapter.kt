package com.example.androidui.recyclerview.operator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.core.bean.Fruit

class OperatorAdapter(private val list: ArrayList<Fruit>) :
    RecyclerView.Adapter<OperatorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_operator, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageResource(list[position].fruitImage)
        holder.name.text = list[position].fruitName
        holder.count.text = list[position].count.toString()
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.fruit_img)
        val name: TextView = itemView.findViewById(R.id.fruit_name)
        val count: TextView = itemView.findViewById(R.id.fruit_count)
        val update: TextView = itemView.findViewById(R.id.update)
    }

    // 在尾部添加单个数据
    fun addItem(item: Fruit) {
        val position = list.size
        list.add(item)
        notifyItemInserted(position)
    }

    // 在指定位置添加单个数据
    fun addItem(position: Int, item: Fruit) {
        list.add(position, item)
        notifyItemInserted(position)
    }

    // 添加多个数据
    fun addItems(position: Int, items: List<Fruit>) {
        list.addAll(position, items)
        notifyItemRangeInserted(position, items.size)
    }

    // 删除指定位置的数据
    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    // 删除多个数据
    fun removeItems(startPosition: Int, count: Int) {
        // 从后往前删，避免索引错乱
        for (i in startPosition + count - 1 downTo startPosition) {
            list.removeAt(i)
        }
        notifyItemRangeRemoved(startPosition, count)
    }

    // 删除所有数据
    fun removeAll() {
        val count = list.size
        list.clear()
        notifyItemRangeRemoved(0, count)
    }

    // 修改指定位置
    fun updateItem(position: Int, newItem: Fruit) {
        list[position] = newItem
        notifyItemChanged(position)
    }

    // 修改多个数据
    fun updateItems(position: Int, newItems: List<Fruit>) {
        for (i in newItems.indices) {
            if (position + i < list.size) {
                list[position + i] = newItems[i]
            }
        }
        notifyItemRangeChanged(position, newItems.size)
    }

    // 移动数据
    fun moveItem(fromPosition: Int, toPosition: Int) {
        val item = list.removeAt(fromPosition)
        list.add(toPosition, item)
        notifyItemMoved(fromPosition, toPosition)
    }
}