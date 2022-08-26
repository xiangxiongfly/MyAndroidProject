package com.example.home.listview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import com.example.home.R
import com.example.home.listview.adapter.MutilTypeAdapter
import com.example.home.listview.bean.LeftFruit
import com.example.home.listview.bean.RightFruit
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE
import java.util.*

class MutilTypeActivity : BaseActivity() {
    private val mList = arrayListOf<Any>()
    private val mFruitImgs = intArrayOf(
        R.drawable.pear_pic,
        R.drawable.apple_pic,
        R.drawable.cherry_pic,
        R.drawable.grape_pic,
        R.drawable.pear_pic,
        R.drawable.strawberry_pic,
    )
    private val mFruitNames = arrayOf<String>(
        "pear",
        "apple",
        "cherry",
        "grape",
        "pear",
        "strawberry",
    )

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MutilTypeActivity::class.java).apply {
                putExtra(KEY_TITLE, "多类型ListView")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutil_type)
        val listView: ListView = findViewById(R.id.listView)
        initData()
        val mAdapter = MutilTypeAdapter(mContext, mList)
        listView.adapter = mAdapter
    }

    private fun initData() {
        val random = Random();
        for (i in 0..30) {
            val index = random.nextInt(mFruitImgs.size)
            val type = random.nextInt(2)
            if (type == 1) {
                mList.add(LeftFruit(mFruitImgs[index], mFruitNames[index]))
            } else {
                mList.add(RightFruit(mFruitImgs[index], mFruitNames[index]))
            }
        }
    }
}