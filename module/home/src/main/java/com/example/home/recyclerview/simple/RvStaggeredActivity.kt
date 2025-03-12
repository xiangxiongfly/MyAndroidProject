package com.example.home.recyclerview.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.base.BaseActivity
import com.example.base.KEY_TITLE
import com.example.home.R
import com.example.home.recyclerview.simple.adapter.FruitAdapter
import com.xiangxiongfly.common.bean.Fruit
import java.util.*

class RvStaggeredActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView

    private val mList = arrayListOf<Fruit>()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RvStaggeredActivity::class.java).apply {
                putExtra(KEY_TITLE, "瀑布流布局")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_staggered)
        initView()
        initData()
        initRv()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initData() {
        for (i in 0..30) {
            mList.add(Fruit(R.drawable.apple_pic, generateString("apple$i")))
            mList.add(Fruit(R.drawable.banana_pic, generateString("banana$i")))
            mList.add(Fruit(R.drawable.cherry_pic, generateString("cherry$i")))
            mList.add(Fruit(R.drawable.grape_pic, generateString("grape$i")))
            mList.add(Fruit(R.drawable.mango_pic, generateString("mango$i")))
            mList.add(Fruit(R.drawable.orange_pic, generateString("orange$i")))
            mList.add(Fruit(R.drawable.pear_pic, generateString("pear$i")))
            mList.add(Fruit(R.drawable.pineapple_pic, generateString("pineapple$i")))
            mList.add(Fruit(R.drawable.strawberry_pic, generateString("strawberry$i")))
            mList.add(Fruit(R.drawable.watermelon_pic, generateString("watermelon$i")))
        }
    }

    private fun initRv() {
        val mAdapter = FruitAdapter(context, mList)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = mAdapter
    }

    private fun generateString(desc: String): String {
        val random = Random()
        val length = random.nextInt(5) + 1
        val builder = StringBuilder()
        for (i in 0 until length) {
            builder.append(desc)
        }
        return builder.toString()
    }


}