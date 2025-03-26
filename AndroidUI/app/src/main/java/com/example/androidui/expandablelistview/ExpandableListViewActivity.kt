package com.example.androidui.expandablelistview

import android.os.Bundle
import android.widget.ExpandableListView
import com.example.androidui.R
import com.example.androidui.expandablelistview.bean.Child
import com.example.androidui.expandablelistview.bean.Group
import com.example.core.base.BaseActivity

class ExpandableListViewActivity : BaseActivity() {
    private lateinit var expandableListView: ExpandableListView

    private val groupList = arrayListOf<Group>()
    private val childList = arrayListOf<ArrayList<Child>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expandable_list_view)
        initView()
        initData()
        initExpandableListView()
    }

    private fun initView() {
        expandableListView = findViewById(R.id.expandableListView)
    }

    private fun initData() {
        groupList.add(Group("西游记"))
        childList.add(arrayListOf<Child>().apply {
            add(Child("唐僧"))
            add(Child("孙悟空"))
            add(Child("猪八戒"))
            add(Child("沙和尚"))
        })

        groupList.add(Group("三国演义"))
        childList.add(arrayListOf<Child>().apply {
            add(Child("刘备"))
            add(Child("关羽"))
            add(Child("赵云"))
            add(Child("张飞"))
            add(Child("马超"))
        })

        groupList.add(Group("水浒传"))
        childList.add(arrayListOf<Child>().apply {
            add(Child("宋江"))
            add(Child("武松"))
            add(Child("吴用"))
            add(Child("鲁智深"))
            add(Child("西门庆"))
            add(Child("潘金莲"))
        })

        groupList.add(Group("红楼梦"))
        childList.add(arrayListOf<Child>().apply {
            add(Child("贾宝玉"))
            add(Child("林黛玉"))
            add(Child("王熙凤"))
        })

        groupList.add(Group("颜色"))
        childList.add(arrayListOf<Child>().apply {
            add(Child("红"))
            add(Child("黄"))
            add(Child("蓝"))
            add(Child("绿"))
            add(Child("青"))
            add(Child("橙"))
            add(Child("紫"))
        })
    }

    private fun initExpandableListView() {
        val mAdapter = ExpandableAdapter(context, groupList, childList)
        expandableListView.setAdapter(mAdapter)
    }
}