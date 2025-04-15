package com.xiangxiongfly.androidstatusbar.operate_status_bar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.xiangxiongfly.androidstatusbar.base.BaseActivity
import com.xiangxiongfly.androidstatusbar.R
import com.xiangxiongfly.androidstatusbar.utils.BarUtils

class OperateStatusBar1Activity : BaseActivity() {
    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, OperateStatusBar1Activity::class.java))
        }
    }

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