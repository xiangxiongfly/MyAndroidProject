package com.example.home.recyclerview.simple

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.bean.Fruit
import com.example.home.data.FruitData
import com.example.home.recyclerview.divider.LinearItemDecoration
import com.example.home.recyclerview.simple.adapter.FruitAdapter
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE
import com.xiangxiongfly.common.utils.dp

class RvLinearActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView

    private val mFruitList = arrayListOf<Fruit>()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RvLinearActivity::class.java).apply {
                putExtra(KEY_TITLE, "线性布局")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_linear)
        initView()
        initData()
        initRv()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initData() {
        mFruitList.clear()
        mFruitList.addAll(FruitData.getFruitList())
    }

    private fun initRv() {
        val mAdapter = FruitAdapter(mContext, mFruitList)
        //设置垂直布局
        recyclerView.layoutManager = LinearLayoutManager(mContext)
        //设置水平布局布局
//        recyclerView.layoutManager =
//            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        //如果itemView的高度一致，可以设置为true，性能优化
        recyclerView.setHasFixedSize(true)
        //分割线使用方式一：
        recyclerView.addItemDecoration(
            LinearItemDecoration(
                LinearItemDecoration.VERTICAL,
                Color.RED,
                0.5.dp
            )
        )
        //分割线使用方式二：
//        recyclerView.addItemDecoration(
//            LinearItemDecoration(
//                mContext,
//                LinearItemDecoration.VERTICAL,
//                R.drawable.divider_red_shape
//            )
//        )
        recyclerView.adapter = mAdapter
    }
}