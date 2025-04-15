package com.xiangxiongfly.androidstatusbar.immersion_status_bar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.xiangxiongfly.androidstatusbar.base.BaseActivity
import com.xiangxiongfly.androidstatusbar.R

class Immersion4Activity : BaseActivity() {
    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, Immersion4Activity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_immersion4)
        val frameLayout = findViewById<FrameLayout>(R.id.frameLayout)
        val btn = findViewById<Button>(R.id.btn)
        val imageView = findViewById<ImageView>(R.id.imageView)

        // 沉浸式效果
        frameLayout.systemUiVisibility =
            SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        // 处理状态栏遮挡问题
        ViewCompat.setOnApplyWindowInsetsListener(btn, object : OnApplyWindowInsetsListener {
            override fun onApplyWindowInsets(
                view: View,
                insets: WindowInsetsCompat
            ): WindowInsetsCompat {
                val layoutParams = view.layoutParams as FrameLayout.LayoutParams
                layoutParams.topMargin = insets.systemWindowInsetTop
                return insets
            }
        })
    }
}