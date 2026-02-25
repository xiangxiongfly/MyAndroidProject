package com.example.androidui.recyclerview

import android.os.Bundle
import android.view.View
import com.example.androidui.R
import com.example.androidui.recyclerview.diffutil.DiffUtilActivity
import com.example.androidui.recyclerview.group.GroupActivity
import com.example.androidui.recyclerview.operator.OperatorActivity
import com.example.androidui.recyclerview.payload.a1.Payload1Activity
import com.example.androidui.recyclerview.payload.a2.Payload2Activity
import com.example.androidui.recyclerview.pool.RecycledViewPoolActivity
import com.example.androidui.recyclerview.simple.MultiTypeActivity
import com.example.androidui.recyclerview.simple.RvGridActivity
import com.example.androidui.recyclerview.simple.RvLinearActivity
import com.example.androidui.recyclerview.simple.RvStaggeredActivity
import com.example.core.base.BaseActivity

class RecyclerViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
    }

    fun toLinear(v: View) {
        RvLinearActivity.actionStart(context)
    }

    fun toGrid(v: View) {
        RvGridActivity.start(context)
    }

    fun toStaggered(v: View) {
        RvStaggeredActivity.start(context)
    }

    fun toMultiType(v: View) {
        MultiTypeActivity.actionStart(context)
    }

    fun toOperator(v: View) {
        OperatorActivity.actionStart(context)
    }

    fun toDiffUtil(v: View) {
        DiffUtilActivity.actionStart(context)
    }

    fun toPayload1(v: View) {
        Payload1Activity.actionStart(context)
    }

    fun toPayload2(v: View) {
        Payload2Activity.actionStart(context)
    }

    fun toGroup(v: View) {
        GroupActivity.actionStart(context)
    }

    fun toPool(v: View) {
        RecycledViewPoolActivity.actionStart(context)
    }
}