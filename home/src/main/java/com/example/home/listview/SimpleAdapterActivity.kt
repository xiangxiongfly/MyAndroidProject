package com.example.home.listview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE

class SimpleAdapterActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SimpleAdapterActivity::class.java).apply {
                putExtra(KEY_TITLE, "SimpleAdapter")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_adapter)
        val listView: ListView = findViewById(R.id.listView)

        val names = arrayOf("苹果", "香蕉", "樱桃", "葡萄", "芒果")
        val imgs = intArrayOf(
            R.drawable.apple_pic, R.drawable.banana_pic, R.drawable.cherry_pic,
            R.drawable.grape_pic, R.drawable.mango_pic
        )
        val datas = ArrayList<Map<String, Any>>()
        for (i in names.indices) {
            val map = HashMap<String, Any>()
            map["name"] = names[i]
            map["img"] = imgs[i]
            datas.add(map)
        }
        listView.adapter = SimpleAdapter(
            mContext,
            datas,
            R.layout.item_fruit,
            arrayOf("name", "img"),
            intArrayOf(R.id.fruit_name, R.id.fruit_img)
        )
    }
}