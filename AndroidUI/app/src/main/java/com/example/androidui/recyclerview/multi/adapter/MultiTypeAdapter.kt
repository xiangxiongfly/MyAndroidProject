package com.example.androidui.recyclerview.multi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.common.bean.LeftFruit
import com.example.common.bean.RightFruit

class MultiTypeAdapter(val mContext: Context, val mList: ArrayList<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_LEFT = 0
        const val TYPE_RIGHT = 1
    }

    private val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun getItemViewType(position: Int): Int {
        return if (mList[position] is LeftFruit) {
            TYPE_LEFT
        } else {
            TYPE_RIGHT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_LEFT) {
            val itemView = layoutInflater.inflate(R.layout.item_left_fruit, parent, false)
            LeftViewHolder(itemView)
        } else {
            val itemView = layoutInflater.inflate(R.layout.item_right_fruit, parent, false)
            RightViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LeftViewHolder) {
            val leftFruit = mList[position] as LeftFruit
            holder.fruitImg.setImageResource(leftFruit.fruitImage)
            holder.fruitName.text = leftFruit.fruitName
        } else if (holder is RightViewHolder) {
            val rightFruit = mList[position] as RightFruit
            holder.fruitImg.setImageResource(rightFruit.fruitImage)
            holder.fruitName.text = rightFruit.fruitName
        }
    }

    override fun getItemCount(): Int = mList.size

    class LeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fruitImg: ImageView = itemView.findViewById(R.id.fruit_img)
        val fruitName: TextView = itemView.findViewById(R.id.fruit_name)
    }

    class RightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fruitImg: ImageView = itemView.findViewById(R.id.fruit_img)
        val fruitName: TextView = itemView.findViewById(R.id.fruit_name)
    }
}