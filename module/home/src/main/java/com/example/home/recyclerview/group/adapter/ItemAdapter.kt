package com.example.home.recyclerview.group.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.recyclerview.group.MyItem

class ItemAdapter(private val context: Context, private val itemList: MutableList<MyItem>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_fruit, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fruitName.text = itemList[position].itemName
        holder.fruitImg.setImageResource(itemList[position].itemImg)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fruitName = itemView.findViewById<TextView>(R.id.fruit_name)
        val fruitImg = itemView.findViewById<ImageView>(R.id.fruit_img)
    }
}