package com.example.androidui.recyclerview.simple

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.androidui.recyclerview.divider.LinearItemDecoration
import com.example.androidui.recyclerview.simple.adapter.FruitAdapter
import com.example.core.base.BaseActivity
import com.example.core.bean.Fruit
import com.example.core.data.FruitData
import com.example.core.exts.dp
import java.util.Collections

class RvLinearActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FruitAdapter

    private val fruitList = arrayListOf<Fruit>()

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, RvLinearActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_operator)
        initView()
        initData()
        initRv()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initData() {
        fruitList.clear()
        fruitList.addAll(FruitData.getFruitList())
    }

    private fun initRv() {
        adapter = FruitAdapter(context, fruitList)
        // 设置垂直布局
        recyclerView.layoutManager = LinearLayoutManager(context)
        // 设置水平布局布局
//        recyclerView.layoutManager =
//            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        // 如果itemView的高度一致，可以设置为true，性能优化
        recyclerView.setHasFixedSize(true)
        // 分割线使用方式一：
//        recyclerView.addItemDecoration(LinearItemDecoration())
        // 分割线使用方式二：
        recyclerView.addItemDecoration(
            LinearItemDecoration(2F.dp, Color.RED, 10.dp, 10.dp)
        )

        recyclerView.adapter = adapter
        initTouch()
    }

    private fun initTouch() {
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
                    Collections.swap(fruitList, viewHolder.adapterPosition, target.adapterPosition)
                    adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                    return false
                }

                //侧滑事件
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    fruitList.removeAt(viewHolder.adapterPosition)
                    adapter.notifyItemRemoved(viewHolder.adapterPosition)
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