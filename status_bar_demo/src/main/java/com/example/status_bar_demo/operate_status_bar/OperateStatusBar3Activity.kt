package com.example.status_bar_demo.operate_status_bar

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.status_bar_demo.R
import com.example.status_bar_demo.base.BaseActivity
import com.example.status_bar_demo.utils.BarUtils

class OperateStatusBar3Activity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operate_status_bar3)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_hide_status_bar -> {
                BarUtils.hideStatusBar(this)
            }
            R.id.btn_show_status_bar -> {
                BarUtils.showStatusBar(this)
            }
            R.id.btn_hide_navigation_bar -> {
                BarUtils.hideNavigationBar(this)
            }
            R.id.btn_show_navigation_bar -> {
                BarUtils.showNavigationBar(this)
            }
            R.id.btn_change_red -> {
                BarUtils.setStatusBarColor(this, Color.RED)
            }
            R.id.btn_change_yellow -> {
                BarUtils.setStatusBarColor(this, Color.YELLOW)
            }
            R.id.btn_change_green -> {
                BarUtils.setStatusBarColorRes(this, R.color.green)
            }
            R.id.btn_other -> {
                Log.e("TAG", "获取状态栏高度；${BarUtils.getStatusBarHeight(mContext)}")
                Log.e("TAG", "获取导航栏高度；${BarUtils.getNavigationBarHeight(mContext)}")
                Log.e("TAG", "是否有导航栏；${BarUtils.isNavigationBar(this)}")
            }
        }
    }
}