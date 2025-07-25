package com.xiangxiongfly.androidstatusbar.statusbar

import android.os.Bundle
import com.xiangxiongfly.androidstatusbar.R
import com.xiangxiongfly.androidstatusbar.base.BaseActivity
import com.xiangxiongfly.androidstatusbar.utils.BarUtils

class FullScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 方式一：fitsNotchScreen+FullScreenTheme主题
        BarUtils.fitsNotchScreen(this)
        BarUtils.hideStatusBar(this)
        BarUtils.hideNavigationBar(this)

        // 方式二：fullScreen+fitsNotchScreen
//        BarUtils.fitsNotchScreen(this)
//        BarUtils.fullScreen(this)
        setContentView(R.layout.activity_status_bar)

    }
}