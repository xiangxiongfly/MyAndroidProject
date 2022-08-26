package com.example.home.listview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.example.home.R
import com.example.home.listview.adapter.MyAdapter
import com.example.home.listview.bean.Fruit
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE

class BaseAdapterActivity : BaseActivity() {
    private val datas = arrayListOf<Fruit>().apply {
        for (i in 0..2) {
            add(Fruit(R.drawable.apple_pic, "apple$i"))
            add(Fruit(R.drawable.banana_pic, "banana$i"))
            add(Fruit(R.drawable.cherry_pic, "cherry$i"))
            add(Fruit(R.drawable.grape_pic, "grape$i"))
            add(Fruit(R.drawable.mango_pic, "mango$i"))
            add(Fruit(R.drawable.orange_pic, "orange$i"))
            add(Fruit(R.drawable.pear_pic, "pear$i"))
            add(Fruit(R.drawable.pineapple_pic, "pineapple$i"))
            add(Fruit(R.drawable.strawberry_pic, "strawberry$i"))
            add(Fruit(R.drawable.watermelon_pic, "watermelon$i"))
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, BaseAdapterActivity::class.java).apply {
                putExtra(KEY_TITLE, "BaseAdapter")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_adapter)
        val listView: ListView = findViewById(R.id.listView)

        val headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_header, null)
        val headerView2 = LayoutInflater.from(mContext).inflate(R.layout.layout_header2, null)
        val footerView = LayoutInflater.from(mContext).inflate(R.layout.layout_footer, null)

        //添加头尾布局
        listView.addHeaderView(headerView)
        listView.addHeaderView(headerView2)
        listView.addFooterView(footerView)

        val mAdapter = MyAdapter(mContext, datas)
        listView.adapter = mAdapter

        //点击事件
        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mAdapter.changeItem(listView, position)
            }
        }
    }
}
