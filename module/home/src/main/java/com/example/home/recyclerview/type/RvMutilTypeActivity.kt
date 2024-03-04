package com.example.home.recyclerview.type

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.xiangxiongfly.common.bean.LeftFruit
import com.xiangxiongfly.common.bean.RightFruit
import com.xiangxiongfly.common.data.FruitData
import com.example.home.recyclerview.type.adapter.MutilTypeAdapter
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE
import java.util.*

class RvMutilTypeActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView

    private val mList = arrayListOf<Any>()
    private val mFruitImgs = FruitData.getFruitImage()
    private val mFruitNames = FruitData.getFruitName()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RvMutilTypeActivity::class.java).apply {
                putExtra(KEY_TITLE, "多类型布局")
            })
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
                mContext,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.adapter = MutilTypeAdapter(mContext, mList)
    }
}