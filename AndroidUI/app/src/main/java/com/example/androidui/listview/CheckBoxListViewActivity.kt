package com.example.androidui.listview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import com.example.androidui.R
import com.example.androidui.listview.adapter.CheckBoxAdapter
import com.example.core.base.BaseActivity
import com.example.core.bean.Fruit

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
            context.startActivity(Intent(context, CheckBoxListViewActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_box_list_view)
        initView()
        initData()
        listView.adapter = CheckBoxAdapter(context, mList)
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