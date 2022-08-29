package com.example.home.listview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.widgets.MyListView

class ScrollViewListViewActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ScrollViewListViewActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_view_list_view)
        val myListView: MyListView = findViewById(R.id.myListView)
        val datas = arrayListOf<String>().apply {
            for (i in 0..30) {
                add(i.toString())
            }
        }
        myListView.adapter =
            ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, datas)
    }
}