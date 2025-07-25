package com.xiangxiongfly.androidstatusbar.statusbar

import android.os.Bundle
import com.xiangxiongfly.androidstatusbar.R
import com.xiangxiongfly.androidstatusbar.base.BaseActivity
import com.xiangxiongfly.androidstatusbar.utils.BarUtils

class FullScreen2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.fitsNotchScreen(this)
        BarUtils.fullScreen2(this)

        setContentView(R.layout.activity_status_bar)
    }
}