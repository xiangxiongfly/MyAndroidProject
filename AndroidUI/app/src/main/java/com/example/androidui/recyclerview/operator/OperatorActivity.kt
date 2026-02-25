package com.example.androidui.recyclerview.operator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.androidui.recyclerview.divider.LinearItemDecoration
import com.example.core.base.BaseActivity
import com.example.core.bean.Fruit
import com.example.core.data.FruitData

class OperatorActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OperatorAdapter

    private val fruitList = arrayListOf<Fruit>()

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, OperatorActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_operator)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initView()
        initData()
        initRv()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_rv, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }

            R.id.addFirstItem -> {
                adapter.addItem(0, Fruit(R.drawable.cherry_pic, "樱桃1号"))
            }

            R.id.addLastItem -> {
                adapter.addItem(Fruit(R.drawable.cherry_pic, "樱桃2号"))
            }

            R.id.addItems -> {
                adapter.addItems(
                    1,
                    listOf(
                        Fruit(R.drawable.cherry_pic, "樱桃3号"),
                        Fruit(R.drawable.cherry_pic, "樱桃4号"),
                        Fruit(R.drawable.cherry_pic, "樱桃5号"),
                    )
                )
            }

            R.id.removeFirstItem -> {
                adapter.removeItem(0)
            }

            R.id.removeItems -> {
                adapter.removeItems(0, 3)
            }

            R.id.removeAll -> {
                adapter.removeAll()
            }

            R.id.updateFirstItem -> {
                adapter.updateItem(0, Fruit(R.drawable.apple_pic, "苹果1号"))
            }

            R.id.updateItems -> {
                adapter.updateItems(
                    0,
                    listOf(
                        Fruit(R.drawable.apple_pic, "苹果2号"),
                        Fruit(R.drawable.apple_pic, "苹果3号"),
                        Fruit(R.drawable.apple_pic, "苹果4号"),
                    )
                )
            }

            R.id.moveItem -> {
                adapter.moveItem(0, adapter.itemCount - 1)
            }
        }
        return true
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initData() {
        fruitList.clear()
        fruitList.addAll(FruitData.getFruitList())
    }

    private fun initRv() {
        adapter = OperatorAdapter(fruitList)
        recyclerView.addItemDecoration(LinearItemDecoration())
        recyclerView.adapter = adapter
    }
}