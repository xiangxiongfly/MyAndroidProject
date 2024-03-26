package com.example.home.recyclerview.diffutil

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.BaseActivity
import com.example.base.KEY_TITLE
import com.example.base.utils.logE
import com.example.home.R
import com.xiangxiongfly.common.bean.User

class DiffUtilActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView

    private lateinit var mAdapter: UserAdapter
    private var mList = ArrayList<User>()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, DiffUtilActivity::class.java).apply {
                putExtra(KEY_TITLE, "DiffUtil测试")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diff_util)
        initView()
        initData()
        initRv()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initData() {
        for (i in 0..15) {
            mList.add(User(i, "name$i", i, "address$i"))
        }
    }

    private fun initRv() {
        mAdapter = UserAdapter(mContext, mList)
        recyclerView.layoutManager = LinearLayoutManager(mContext)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                mContext, DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.adapter = mAdapter
    }

    var start: Long = 0

    fun click1(v: View) {
        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val end = System.currentTimeMillis()
                    logE("DiffUtil耗时1：${end - start}ms")
                    recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
        start = System.currentTimeMillis()
        val newList = ArrayList<User>()
        for (item in mList) {
            newList.add(item.copy())
        }
        newList.removeFirst()
        newList.removeLast()
        newList[1].address = "修改了1"
        newList[10].address = "修改了10"
        val result: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(MyDiffUtilCallback(mList, newList), true)
        result.dispatchUpdatesTo(mAdapter)
        mAdapter.setData(newList)
    }

    fun click2(v: View) {
        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val end = System.currentTimeMillis()
                    logE("DiffUtil耗时2：${end - start}ms")
                    recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            },
        )
        start = System.currentTimeMillis()
        val newList = ArrayList<User>()
        for (item in mList) {
            newList.add(item.copy())
        }
        newList.removeFirst()
        newList.removeLast()
        val result: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(MyDiffUtilCallback(mList, newList), true)
        result.dispatchUpdatesTo(mAdapter)
        mAdapter.setData(newList)
    }

    fun click3(v: View) {
        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val end = System.currentTimeMillis()
                    logE("notifyDataSetChanged耗时1：${end - start}ms")
                    recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
        start = System.currentTimeMillis()
        mList.removeFirst()
        mList.removeLast()
        mList[1].address = "修改了1"
        mList[10].address = "修改了10"
        mAdapter.notifyDataSetChanged()
    }

    fun click4(v: View) {
        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val end = System.currentTimeMillis()
                    logE("notifyDataSetChanged耗时2：${end - start}ms")
                    recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
        start = System.currentTimeMillis()
        mList.removeFirst()
        mList.removeLast()
        mAdapter.notifyDataSetChanged()
    }
}

