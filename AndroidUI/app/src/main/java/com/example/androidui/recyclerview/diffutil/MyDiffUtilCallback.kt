package com.example.home.recyclerview.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.core.bean.User

class MyDiffUtilCallback(
    private val oldList: ArrayList<User>,
    private val newList: ArrayList<User>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old.name == new.name && old.age == new.age && old.address == new.address
    }
}