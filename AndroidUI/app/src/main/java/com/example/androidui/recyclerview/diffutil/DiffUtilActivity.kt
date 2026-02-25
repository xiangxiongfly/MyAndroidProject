package com.example.androidui.recyclerview.diffutil

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.androidui.recyclerview.divider.LinearItemDecoration
import com.example.core.base.BaseActivity
import com.example.core.bean.User

class DiffUtilActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    private var list = ArrayList<User>()

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, DiffUtilActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diffutil)
        initView()
        initData()
        initRv()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initData() {
        for (i in 0..15) {
            list.add(User(i, "name$i", i, "address$i"))
        }
    }

    private fun initRv() {
        adapter = UserAdapter()
        recyclerView.addItemDecoration(LinearItemDecoration())
        recyclerView.adapter = adapter
        adapter.submitList(list)
    }

    fun click1(v: View) {
        val newList = list.toMutableList()
        newList.removeFirst()
        newList.removeLast()
        newList[1].address = "修改了修改了修改了"
        newList[6].address = "修改了修改了修改了修改了修改了修改了"

        adapter.submitList(newList)
    }
}

