package com.example.home.recyclerview.diffutil

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.bean.User

class UserAdapter(val mContext: Context, private var mList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    fun setData(newList: ArrayList<User>) {
        mList.clear()
        mList.addAll(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_user, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = mList[position].id.toString()
        holder.name.text = mList[position].name
        holder.age.text = mList[position].age.toString()
        holder.address.text = mList[position].address
    }

    override fun getItemCount(): Int = mList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.id)
        val name: TextView = itemView.findViewById(R.id.name)
        val age: TextView = itemView.findViewById(R.id.age)
        val address: TextView = itemView.findViewById(R.id.address)
    }
}