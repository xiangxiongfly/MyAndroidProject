package com.example.home.immersion

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE

class ImmersionActivity2 : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ImmersionActivity2::class.java).apply {
                putExtra(KEY_TITLE, "情况二(CoordinatorLayout)")
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_immersion2)
        window.statusBarColor = Color.TRANSPARENT
    }
}