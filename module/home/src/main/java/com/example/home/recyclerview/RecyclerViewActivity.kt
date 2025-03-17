package com.example.home.recyclerview

import android.os.Bundle
import android.view.View
import com.example.base.BaseActivity
import com.example.home.R
import com.example.home.recyclerview.diffutil.DiffUtilActivity
import com.example.home.recyclerview.group.GroupActivity
import com.example.home.recyclerview.simple.RvGridActivity
import com.example.home.recyclerview.simple.RvLinearActivity
import com.example.home.recyclerview.simple.RvStaggeredActivity
import com.example.home.recyclerview.type.RvMutilTypeActivity

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