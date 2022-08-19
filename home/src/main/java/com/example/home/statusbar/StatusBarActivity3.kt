package com.example.home.statusbar

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE
import com.xiangxiongfly.common.utils.StatusBarUtils

class StatusBarActivity3 : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, StatusBarActivity3::class.java).apply {
                putExtra(KEY_TITLE, "修改状态栏颜色")
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_bar3)
    }

    fun switchRed(v: View) {
        StatusBarUtils.setStatusBarColor(this, Color.RED)
    }

    fun switchYellow(v: View) {
        StatusBarUtils.setStatusBarColor2(this, R.color.yellow)
    }

    fun switchGreen(v: View) {
        StatusBarUtils.setStatusBarColor2(this, R.color.green)
    }
}
