package com.example.home.listview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import com.example.home.R
import com.example.home.listview.adapter.CheckBoxAdapter
import com.example.home.bean.Fruit
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE

class CheckBoxListViewActivity : BaseActivity() {
    private lateinit var listView: ListView

    private val mList = arrayListOf<Fruit>()
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
            context.startActivity(Intent(context, CheckBoxListViewActivity::class.java).apply {
                putExtra(KEY_TITLE, "CheckBox+ListView")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_box_list_view)
        initView()
        initData()
        listView.adapter = CheckBoxAdapter(mContext, mList)
    }

    private fun initView() {
        listView = findViewById(R.id.listView)
    }

    private fun initData() {
        for (i in 0..5) {
            for (j in mFruitImgs.indices) {
                mList.add(Fruit(mFruitImgs[j], "${mFruitNames[j]}$i"))
            }
        }
    }
}