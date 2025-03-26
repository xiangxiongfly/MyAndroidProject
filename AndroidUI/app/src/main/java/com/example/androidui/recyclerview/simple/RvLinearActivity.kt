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
import com.example.androidui.recyclerview.simple.adapter.FruitLinearAdapter
import com.example.core.base.BaseActivity
import com.example.core.bean.Fruit
import com.example.core.data.FruitData
import com.example.core.utils.dp
import java.util.*

class RvLinearActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var adapter: FruitLinearAdapter

    private val fruitList = arrayListOf<Fruit>()

    companion object {
        fun actionStart(context: Context) {
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
            R.id.addAll -> {
                val fruits = arrayListOf<Fruit>().apply {
                    add(Fruit(R.drawable.cherry_pic, "樱桃1号"))
                    add(Fruit(R.drawable.cherry_pic, "樱桃2号"))
                }
                adapter.addRange(0, fruits)
            }
            R.id.deleteAll -> {
                adapter.deleteRange(1, 2)
            }
            R.id.updateAll -> {
                adapter.updateRange(1, 2)
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
        fruitList.clear()
        fruitList.addAll(FruitData.getFruitList())
    }

    private fun initRv() {
        adapter = FruitLinearAdapter(context, fruitList)
        // 设置点击事件
        adapter.setOnItemClickListener(object : FruitLinearAdapter.OnItemClickListener {
            override fun onUpdate(position: Int) {
                adapter.update(position)
            }

            override fun onAdd(position: Int) {
                val fruit = Fruit(FruitData.getFruitImages().random(), "新水果")
                adapter.add(position + 1, fruit)
            }

            override fun onDelete(position: Int) {
                adapter.delete(position)
            }
        })
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