package com.example.androidui.recyclerview.pool

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.core.bean.User

class ItemAdapter(private val id: Int, private val items: List<User>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ItemViewHolder(id, view)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        Log.e("TAG", "绑定ViewHolder-${id} ${holder.hashCode()}")
        items[position].let {
            holder.id.text = it.id.toString()
            holder.name.text = it.name
            holder.age.text = it.age.toString()
            holder.address.text = it.address
        }
    }

    override fun onViewRecycled(holder: ItemViewHolder) {
        super.onViewRecycled(holder)
        Log.e("TAG", "回收ViewHolder-${id} ${holder.hashCode()}")
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(id: Int, itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById<TextView>(R.id.id)
        val name: TextView = itemView.findViewById<TextView>(R.id.name)
        val age: TextView = itemView.findViewById<TextView>(R.id.age)
        val address: TextView = itemView.findViewById<TextView>(R.id.address)

        init {
            Log.e("TAG", "创建ViewHolder-$id: ${hashCode()}")
        }
    }
}