package com.example.home.recyclerview.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.xiangxiongfly.common.bean.Fruit
import com.xiangxiongfly.common.data.FruitData
import com.example.home.recyclerview.divider.GridItemDecoration
import com.example.home.recyclerview.simple.adapter.FruitAdapter
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE

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
        recyclerView.layoutManager = GridLayoutManager(mContext, 3)
        recyclerView.setHasFixedSize(true)
        //方式一
//        recyclerView.addItemDecoration(GridItemDecoration(Color.RED, 1.dp))
        //方式二
        recyclerView.addItemDecoration(GridItemDecoration(mContext, R.drawable.divider_red_shape))
        recyclerView.adapter = mAdapter
    }
}