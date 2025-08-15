package com.example.androidui.recyclerview.multi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.androidui.recyclerview.multi.adapter.MultiTypeAdapter
import com.example.common.bean.LeftFruit
import com.example.common.bean.RightFruit
import com.example.core.base.BaseActivity
import com.example.core.data.FruitData
import java.util.Random

class MultiTypeActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView

    private val mList = arrayListOf<Any>()
    private val mFruitImgs = FruitData.getFruitImages()
    private val mFruitNames = FruitData.getFruitNames()

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, MultiTypeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_mutil_type)
        initView()
        initData()
        initRv()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initData() {
        val random = Random()
        for (i in 0..30) {
            val index = random.nextInt(mFruitImgs.size)
            val type = random.nextInt(2)
            mList.add(
                if (type == 1) {
                    LeftFruit(mFruitImgs[index], mFruitNames[index])
                } else {
                    RightFruit(mFruitImgs[index], mFruitNames[index])
                }
            )
        }
    }

    private fun initRv() {
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.adapter = MultiTypeAdapter(context, mList)
    }
}