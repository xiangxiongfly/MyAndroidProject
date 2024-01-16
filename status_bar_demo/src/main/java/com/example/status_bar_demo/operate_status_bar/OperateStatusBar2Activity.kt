package com.example.status_bar_demo.operate_status_bar

import android.os.Bundle
import com.example.status_bar_demo.R
import com.example.status_bar_demo.base.BaseActivity
import com.example.status_bar_demo.utils.BarUtils

class OperateStatusBar2Activity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.fitsNotchScreen(this)
        BarUtils.fullScreen2(this)
        setContentView(R.layout.activity_operate_status_bar2)
    }
}