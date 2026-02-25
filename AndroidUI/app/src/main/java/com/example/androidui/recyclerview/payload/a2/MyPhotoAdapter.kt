package com.example.androidui.recyclerview.payload.a2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidui.R
import com.example.androidui.recyclerview.payload.MyPhoto

class MyPhotoAdapter : RecyclerView.Adapter<MyPhotoAdapter.ViewHolder>() {
    private var items = emptyList<MyPhoto>()
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = items[position]
        Glide.with(holder.tvPhoto)
            .load(item.url)
            .into(holder.tvPhoto)
        holder.tvName.text = item.name
        holder.tvCount.text = item.count.toString()
    }

    override fun getItemCount() = items.size

    fun getItems() = items

    fun submitList(newItems: List<MyPhoto>) {
        val diffCallback = MyDiffUtilCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    class MyDiffUtilCallback(private val oldItems: List<MyPhoto>, private val newItems: List<MyPhoto>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItems.size
        }

        override fun getNewListSize(): Int {
            return newItems.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition].id == newItems[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[oldItemPosition]
            return oldItem.url == newItem.url && oldItem.name == newItem.name && oldItem.count == newItem.count
        }

        // 生成payload，只包含变化的字段
        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]
            val payloads = mutableMapOf<String, Any>()
            // 只将变化的字段添加到 payload
            if (oldItem.name != newItem.name) {
                payloads["name"] = newItem.name
            }
            if (oldItem.count != newItem.count) {
                payloads["count"] = newItem.count
            }
            return if (payloads.isEmpty()) null else payloads
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPhoto: ImageView = itemView.findViewById<ImageView>(R.id.tv_photo)
        val tvName: TextView = itemView.findViewById<TextView>(R.id.tv_name)
        val tvCount: TextView = itemView.findViewById<TextView>(R.id.tv_count)
        val btnCount: Button = itemView.findViewById<Button>(R.id.btn_count)
        val btnName: Button = itemView.findViewById<Button>(R.id.btn_name)

        init {
            btnCount.setOnClickListener {
                val position = bindingAdapterPosition
                onItemClickListener?.onItemCountClick(position)
            }
            btnName.setOnClickListener {
                val position = bindingAdapterPosition
                onItemClickListener?.onItemNameClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemCountClick(position: Int)
        fun onItemNameClick(position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}