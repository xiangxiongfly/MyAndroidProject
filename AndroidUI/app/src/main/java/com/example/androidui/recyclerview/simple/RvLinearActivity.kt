package com.example.androidui.recyclerview.simple

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.androidui.recyclerview.divider.LinearItemDecoration
import com.example.core.base.BaseActivity
import com.example.androidui.recyclerview.simple.adapter.FruitAdapter2
import com.example.core.bean.Fruit
import com.example.core.data.FruitData
import com.example.core.utils.dp
import java.util.*

class RvLinearActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var mAdapter: FruitAdapter2

    private val mFruitList = arrayListOf<Fruit>()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RvLinearActivity::class.java))
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
        mAdapter = FruitAdapter2(context, mFruitList)
        //设置点击事件
        mAdapter.setOnItemClickListener(object : FruitAdapter2.OnItemClickListener {
            override fun onUpdate(position: Int) {
                mAdapter.update(position)
            }

            override fun onDelete(position: Int) {
                mAdapter.delete(position)
            }
        })
        //设置垂直布局
        recyclerView.layoutManager = LinearLayoutManager(context)
        //设置水平布局布局
//        recyclerView.layoutManager =
//            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        //如果itemView的高度一致，可以设置为true，性能优化
        recyclerView.setHasFixedSize(true)
        //分割线使用方式一：
        recyclerView.addItemDecoration(
            LinearItemDecoration(
                LinearItemDecoration.VERTICAL_LIST,
                Color.RED,
                1.dp
            )
        )
        //分割线使用方式二：
//        recyclerView.addItemDecoration(
//            LinearItemDecoration(
//                mContext,
//                LinearItemDecoration.VERTICAL_LIST,
//                R.drawable.divider_red_shape
//            )
//        )

        recyclerView.adapter = mAdapter
        initBind()
    }

    private fun initBind() {
        val helper = ItemTouchHelper(
            object : ItemTouchHelper.Callback() {
                override fun getMovementFlags(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder
                ): Int {
                    //侧滑删除
                    val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                    //拖拽
                    val dragFlags =
                        ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                    return makeMovementFlags(dragFlags, swipeFlags)
                }

                //拖拽事件
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    Collections.swap(mFruitList, viewHolder.adapterPosition, target.adapterPosition)
                    mAdapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                    return false
                }

                //侧滑事件
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    mFruitList.removeAt(viewHolder.adapterPosition)
                    mAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                }

                //是否拖拽
                override fun isLongPressDragEnabled(): Boolean {
                    return true
                }
            }
        )
        //绑定
        helper.attachToRecyclerView(recyclerView)
    }


}