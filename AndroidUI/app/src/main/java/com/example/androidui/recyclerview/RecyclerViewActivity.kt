package com.example.androidui.recyclerview

import android.os.Bundle
import android.view.View
import com.example.androidui.R
import com.example.androidui.recyclerview.group.GroupActivity
import com.example.androidui.recyclerview.simple.RvGridActivity
import com.example.androidui.recyclerview.simple.RvLinearActivity
import com.example.androidui.recyclerview.simple.RvStaggeredActivity
import com.example.androidui.recyclerview.type.RvMutilTypeActivity
import com.example.core.base.BaseActivity
import com.example.androidui.recyclerview.diffutil.DiffUtilActivity

class RecyclerViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
    }

    fun toLinear(v: View) {
        RvLinearActivity.start(this)
    }

    fun toGrid(v: View) {
        RvGridActivity.start(this)
    }

    fun toStaggered(v: View) {
        RvStaggeredActivity.start(this)
    }

    fun toMutilType(v: View) {
        RvMutilTypeActivity.start(this)
    }

    fun toDiffUtil(v: View) {
        DiffUtilActivity.start(this)
    }

    fun toGroup(v:View){
        GroupActivity.actionStart(context)
    }
}