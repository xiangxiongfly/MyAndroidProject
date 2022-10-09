package com.example.home.listview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import com.example.home.R
import com.xiangxiongfly.common.bean.LeftFruit
import com.xiangxiongfly.common.bean.RightFruit
import com.xiangxiongfly.common.data.FruitData
import com.example.home.listview.adapter.MutilTypeAdapter
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE
import java.util.*

class LvMutilTypeActivity : BaseActivity() {
    private val mList = arrayListOf<Any>()
    private val mFruitImgs = FruitData.getFruitImage()
    private val mFruitNames = FruitData.getFruitName()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LvMutilTypeActivity::class.java).apply {
                putExtra(KEY_TITLE, "多类型ListView")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lv_mutil_type)
        val listView: ListView = findViewById(R.id.listView)
        initData()
        val mAdapter = MutilTypeAdapter(mContext, mList)
        listView.adapter = mAdapter
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
}