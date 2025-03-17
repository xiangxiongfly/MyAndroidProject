package com.example.androidui.recyclerview.group

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.androidui.recyclerview.group.adapter.ItemAdapter
import com.example.androidui.recyclerview.group.adapter.TitleAdapter
import com.example.core.base.BaseActivity
import com.example.home.recyclerview.group.MyGroup
import com.example.home.recyclerview.group.MyItem

class GroupActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private val list = mutableListOf<MyGroup>()

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, GroupActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)
        recyclerView = findViewById(R.id.recycler_view)
        initData()
        initRV()
    }

    private fun initRV() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
        val concatAdapter = ConcatAdapter()
        recyclerView.adapter = concatAdapter

        list.forEach {
            val titleAdapter = TitleAdapter(context, mutableListOf(it.groupName))
            concatAdapter.addAdapter(titleAdapter)
            val itemAdapter = ItemAdapter(context, it.list)
            concatAdapter.addAdapter(itemAdapter)
        }
    }

    private fun initData() {
        for (i in 0..10) {
            val itemList = mutableListOf<MyItem>()
            for (j in 0..10) {
                itemList.add(MyItem("item${j}", randomFruit()))
            }
            val group = MyGroup("分组${i}", itemList)
            list.add(group)
        }
    }

    private fun randomFruit(): Int {
        val fruits = listOf<Int>(R.drawable.apple_pic, R.drawable.banana_pic, R.drawable.cherry_pic)
        return fruits.random()
    }
}