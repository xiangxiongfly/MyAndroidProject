package com.example.home.recyclerview.simple.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.bean.Fruit

class FruitAdapter(val mContext: Context, private val mList: ArrayList<Fruit>) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_fruit2, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageResource(mList[position].fruitImage)
        holder.name.text = mList[position].fruitName
    }

    override fun getItemCount(): Int = mList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView
        val name: TextView

        init {
            img = itemView.findViewById(R.id.fruit_img)
            name = itemView.findViewById(R.id.fruit_name)
        }
    }
}