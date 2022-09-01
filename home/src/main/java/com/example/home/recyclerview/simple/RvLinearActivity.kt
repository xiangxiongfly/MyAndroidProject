package com.example.home.recyclerview.simple

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.bean.Fruit
import com.example.home.data.FruitData
import com.example.home.recyclerview.divider.LinearItemDecoration
import com.example.home.recyclerview.simple.adapter.FruitAdapter2
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE
import com.xiangxiongfly.common.utils.dp

class RvLinearActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var mAdapter: FruitAdapter2

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
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initData()
        initRv()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.rv_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.add -> {
                mAdapter.add(0, Fruit(R.drawable.banana_pic, "香蕉1号"))
                recyclerView.scrollToPosition(0)
            }
            R.id.addAll -> {
                val fruits = arrayListOf<Fruit>().apply {
                    add(Fruit(R.drawable.cherry_pic, "樱桃1号"))
                    add(Fruit(R.drawable.cherry_pic, "樱桃2号"))
                    add(Fruit(R.drawable.cherry_pic, "樱桃3号"))
                }
                mAdapter.addRange(2, fruits)
            }
            R.id.deleteAll -> {
                mAdapter.deleteRange(1, 3)
            }
            R.id.updateAll -> {
                mAdapter.updateRange(1, 3)
            }
        }
        return true
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun initData() {
        mFruitList.clear()
        mFruitList.addAll(FruitData.getFruitList())
    }

    private fun initRv() {
        mAdapter = FruitAdapter2(mContext, mFruitList)
        mAdapter.setOnItemClickListener(object : FruitAdapter2.OnItemClickListener {
            override fun onUpdate(position: Int) {
                mAdapter.update(position)
            }

            override fun onDelete(position: Int) {
                mAdapter.delete(position)
            }
        })
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