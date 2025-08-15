package com.example.androidui.recyclerview.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.androidui.recyclerview.divider.GridItemDecoration
import com.example.androidui.recyclerview.simple.adapter.FruitAdapter
import com.example.core.base.BaseActivity
import com.example.core.bean.Fruit
import com.example.core.data.FruitData

class RvGridActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView

    private val mFruitList = arrayListOf<Fruit>()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RvGridActivity::class.java))
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
        val mAdapter = FruitAdapter(context, mFruitList)
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.setHasFixedSize(true)
        //方式一
//        recyclerView.addItemDecoration(GridItemDecoration(Color.RED, 1.dp))
        //方式二
        recyclerView.addItemDecoration(GridItemDecoration(context, R.drawable.divider_red_shape))
        recyclerView.adapter = mAdapter
    }
}