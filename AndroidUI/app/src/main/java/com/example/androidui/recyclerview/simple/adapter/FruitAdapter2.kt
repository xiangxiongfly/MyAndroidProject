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

class FruitAdapter2(val mContext: Context, private val mList: ArrayList<Fruit>) :
    RecyclerView.Adapter<FruitAdapter2.ViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_fruit_add_delete, parent, false)
        val viewHolder = ViewHolder(itemView)
        viewHolder.update.setOnClickListener {
            mOnItemClickListener?.onUpdate(viewHolder.adapterPosition)
        }
        viewHolder.delete.setOnClickListener {
            mOnItemClickListener?.onDelete(viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageResource(mList[position].fruitImage)
        holder.name.text = mList[position].fruitName
    }

    override fun getItemCount(): Int = mList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.fruit_img)
        val name: TextView = itemView.findViewById(R.id.fruit_name)
        val update: TextView = itemView.findViewById(R.id.update)
        val delete: TextView = itemView.findViewById(R.id.delete)
    }

    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onUpdate(position: Int)
        fun onDelete(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mOnItemClickListener = listener
    }

    fun add(position: Int, fruit: Fruit) {
        mList.add(0, fruit)
        notifyItemInserted(position)
    }

    fun addRange(position: Int, fruits: ArrayList<Fruit>) {
        mList.addAll(position, fruits)
        notifyItemRangeInserted(position, fruits.size)
    }

    fun delete(position: Int) {
        mList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun deleteRange(position: Int, count: Int) {
        val tmpList = arrayListOf<Fruit>()
        for (i in position until position + count) {
            tmpList.add(mList[i])
        }
        mList.removeAll(tmpList)
        notifyItemRangeRemoved(position, count)
    }

    fun update(position: Int) {
        mList[position].fruitName = "修改了$position"
        notifyItemChanged(position)
    }

    fun updateRange(position: Int, count: Int) {
        for (i in position until position + count) {
            mList[i].fruitName = "修改了$i"
        }
        notifyItemRangeChanged(position, count)
    }

}