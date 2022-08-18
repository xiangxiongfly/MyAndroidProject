package com.example.home.immersion

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.widget.Button
import android.widget.FrameLayout
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE

class ImmersionActivity4 : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ImmersionActivity4::class.java).apply {
                putExtra(KEY_TITLE, "情况四(FrameLayout实现)")
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_immersion4)
        window.statusBarColor = Color.TRANSPARENT
        val frameLayout: FrameLayout = findViewById(R.id.frame_layout)
        val button: Button = findViewById(R.id.button)

        //沉浸式效果：
        frameLayout.systemUiVisibility =
            SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        //状态栏遮挡问题：
        ViewCompat.setOnApplyWindowInsetsListener(button, object : OnApplyWindowInsetsListener {
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