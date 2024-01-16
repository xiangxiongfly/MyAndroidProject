package com.example.status_bar_demo.operate_status_bar

import android.os.Bundle
import com.example.status_bar_demo.R
import com.example.status_bar_demo.base.BaseActivity
import com.example.status_bar_demo.utils.BarUtils


class OperateStatusBar1Activity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 方式一：配合FullScreenTheme主题
        BarUtils.fitsNotchScreen(this)

        // 方式二：全屏
        BarUtils.fullScreen(this)
        // 隐藏状态栏
//        BarUtils.hideStatusBar(this)

        setContentView(R.layout.activity_operate_status_bar1)
    }
}