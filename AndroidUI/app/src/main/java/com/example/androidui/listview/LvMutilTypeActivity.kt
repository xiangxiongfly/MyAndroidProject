package com.example.androidui.listview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import com.example.androidui.R
import com.example.androidui.listview.adapter.MutilTypeAdapter
import com.example.common.bean.LeftFruit
import com.example.common.bean.RightFruit
import com.example.core.base.BaseActivity
import com.example.core.data.FruitData
import java.util.Random

class LvMutilTypeActivity : BaseActivity() {
    private val mList = arrayListOf<Any>()
    private val mFruitImgs = FruitData.getFruitImages()
    private val mFruitNames = FruitData.getFruitNames()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LvMutilTypeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lv_mutil_type)
        val listView: ListView = findViewById(R.id.listView)
        initData()
        val mAdapter = MutilTypeAdapter(context, mList)
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