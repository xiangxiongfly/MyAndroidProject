package com.xiangxiongfly.androidstatusbar.operate_status_bar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.xiangxiongfly.androidstatusbar.base.BaseActivity
import com.xiangxiongfly.androidstatusbar.R
import com.xiangxiongfly.androidstatusbar.utils.BarUtils

class OperateStatusBar2Activity : BaseActivity() {
    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, OperateStatusBar2Activity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.fitsNotchScreen(this)
        BarUtils.fullScreen2(this)
        setContentView(R.layout.activity_operate_status_bar2)
    }
}