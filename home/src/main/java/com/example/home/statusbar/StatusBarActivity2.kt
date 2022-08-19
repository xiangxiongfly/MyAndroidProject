package com.example.home.statusbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE


class StatusBarActivity2 : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, StatusBarActivity2::class.java).apply {
                putExtra(KEY_TITLE, "全屏+状态栏有文字图标")
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_bar2)
//        window.statusBarColor= Color.TRANSPARENT //可以替换为主题
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}
