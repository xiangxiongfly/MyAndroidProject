package com.example.androidui.recyclerview.diffutil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.core.bean.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var items = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = items[position].id.toString()
        holder.name.text = items[position].name
        holder.age.text = items[position].age.toString()
        holder.address.text = items[position].address
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.id)
        val name: TextView = itemView.findViewById(R.id.name)
        val age: TextView = itemView.findViewById(R.id.age)
        val address: TextView = itemView.findViewById(R.id.address)
    }

    class MyDiffUtilCallback(
        private val oldList: List<User>,
        private val newList: List<User>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        // 唯一标识比较
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        // 内容比较
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = oldList[oldItemPosition]
            val new = newList[newItemPosition]
            return old.name == new.name && old.age == new.age && old.address == new.address
        }
    }

    // 使用DiffUtil更新数据
    fun submitList(newItems: List<User>) {
        val diffCallback = MyDiffUtilCallback(items, newItems)
        val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        result.dispatchUpdatesTo(this)
        items = newItems
    }
}