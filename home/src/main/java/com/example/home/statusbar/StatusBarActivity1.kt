package com.example.home.statusbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE
import com.xiangxiongfly.common.utils.BarState
import com.xiangxiongfly.common.utils.StatusBarUtils

class StatusBarActivity1 : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, StatusBarActivity1::class.java).apply {
                putExtra(KEY_TITLE, "全屏+状态栏无文字图标")
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_bar1)

        //方式一：设置全屏主题
        StatusBarUtils.fitsNotchScreen(this)


        //方式二：
        //隐藏状态栏
//        StatusBarUtils.hideBar(this, BarState.FLAG_HIDE_STATUS_BAR)
        //隐藏状态栏和底部导航栏
        StatusBarUtils.hideBar(this, BarState.FLAG_HIDE_ALL_BAR)
        //隐藏底部导航栏
//        StatusBarUtils.hideBar(this, BarState.FLAG_HIDE_NAVIGATION_BAR)
        //显示全部
//        StatusBarUtils.hideBar(this,BarState.FLAG_SHOW_ALL_BAR)

    }
}
