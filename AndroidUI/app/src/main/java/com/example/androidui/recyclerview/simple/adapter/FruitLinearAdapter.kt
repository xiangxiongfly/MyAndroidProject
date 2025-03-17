package com.example.androidui.recyclerview.simple.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.core.bean.Fruit

class FruitLinearAdapter(val context: Context, private val list: ArrayList<Fruit>) :
    RecyclerView.Adapter<FruitLinearAdapter.ViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_fruit_linear, parent, false)
        val viewHolder = ViewHolder(itemView)
        viewHolder.update.setOnClickListener {
            onItemClickListener?.onUpdate(viewHolder.absoluteAdapterPosition)
        }
        viewHolder.add.setOnClickListener {
            onItemClickListener?.onAdd(viewHolder.absoluteAdapterPosition)
        }
        viewHolder.delete.setOnClickListener {
            onItemClickListener?.onDelete(viewHolder.absoluteAdapterPosition)
        }
        return viewHolder
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
        val add: TextView = itemView.findViewById(R.id.add)
        val delete: TextView = itemView.findViewById(R.id.delete)
    }

    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onUpdate(position: Int)
        fun onAdd(position: Int)
        fun onDelete(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun add(position: Int, fruit: Fruit) {
        list.add(position, fruit)
        notifyItemInserted(position)
    }

    fun addRange(position: Int, fruits: ArrayList<Fruit>) {
        list.addAll(position, fruits)
        notifyItemRangeInserted(position, fruits.size)
    }

    fun delete(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun deleteRange(position: Int, count: Int) {
        val tmpList = arrayListOf<Fruit>()
        for (i in position until position + count) {
            tmpList.add(list[i])
        }
        list.removeAll(tmpList)
        notifyItemRangeRemoved(position, count)
    }

    fun update(position: Int) {
        list[position].count++
        notifyItemChanged(position)
    }

    fun updateRange(position: Int, count: Int) {
        for (i in position until position + count) {
            list[i].count++
        }
        notifyItemRangeChanged(position, count)
    }
}