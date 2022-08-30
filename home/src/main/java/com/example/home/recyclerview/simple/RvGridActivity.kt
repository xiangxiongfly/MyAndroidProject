package com.example.home.recyclerview.simple

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.bean.Fruit
import com.example.home.data.FruitData
import com.example.home.recyclerview.divider.GridItemDecoration
import com.example.home.recyclerview.simple.adapter.FruitAdapter
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE
import com.xiangxiongfly.common.utils.dp

class RvGridActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView

    private val mFruitList = arrayListOf<Fruit>()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RvGridActivity::class.java).apply {
                putExtra(KEY_TITLE, "网格布局")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_grid)
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
        recyclerView.layoutManager = GridLayoutManager(mContext, 2)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(GridItemDecoration(Color.RED, 0.5.dp))
        recyclerView.adapter = mAdapter
    }
}