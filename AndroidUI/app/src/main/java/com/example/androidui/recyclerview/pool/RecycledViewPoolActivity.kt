package com.example.androidui.recyclerview.pool

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.core.base.BaseActivity
import com.example.core.bean.User

class RecycledViewPoolActivity : BaseActivity() {
    private lateinit var rv1: RecyclerView
    private lateinit var rv2: RecyclerView

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, RecycledViewPoolActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycled_view_pool)
        rv1 = findViewById(R.id.rv1)
        rv2 = findViewById(R.id.rv2)

        // 创建RecycledViewPool
        val sharedPool = RecyclerView.RecycledViewPool()

        rv1.apply {
            val id = 1
            setRecycledViewPool(sharedPool)
            adapter = ItemAdapter(id, generateItems(0))
        }
        rv2.apply {
            val id = 2
            setRecycledViewPool(sharedPool)
            adapter = ItemAdapter(id, generateItems(1))
        }
    }

    fun generateItems(position: Int): List<User> {
        return List(50) { User(it, "name-$it-$position", it, "address$it") }
    }
}