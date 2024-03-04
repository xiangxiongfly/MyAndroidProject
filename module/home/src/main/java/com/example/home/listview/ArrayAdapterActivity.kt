package com.example.home.listview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE

class ArrayAdapterActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ArrayAdapterActivity::class.java).apply {
                putExtra(KEY_TITLE, "ArrayAdapter")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_array_adapter)
        val listView: ListView = findViewById(R.id.listView)
        val datas = arrayOf(
            "yellow",
            "white",
            "black",
            "blue",
            "pink",
            "red",
            "orange",
            "green",
            "gray",
            "红",
            "绿",
            "蓝",
            "黑"
        )
        listView.adapter = ArrayAdapter(mContext, android.R.layout.simple_list_item_1, datas)
    }
}