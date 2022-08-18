package com.example.home.immersion

import android.os.Bundle
import android.view.View
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity

class ImmersionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_immersion)
    }

    fun click1(v: View) {
        ImmersionActivity1.start(this)
    }

    fun click2(v: View) {
        ImmersionActivity2.start(this)
    }

    fun click3(v: View) {
        ImmersionActivity3.start(this)
    }

    fun click4(v: View) {
        ImmersionActivity4.start(this)
    }

    fun click5(v: View) {
        ImmersionActivity5.start(this)
    }
}