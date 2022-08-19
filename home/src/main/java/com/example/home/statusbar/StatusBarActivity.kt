package com.example.home.statusbar

import android.os.Bundle
import android.view.View
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity


class StatusBarActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_bar)
    }

    fun click1(v: View) {
        StatusBarActivity1.start(this)
    }

    fun click2(v: View) {
        StatusBarActivity2.start(this)
    }


}