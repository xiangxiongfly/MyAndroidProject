package com.xiangxiongfly.myandroid

import android.os.Bundle
import com.xiangxiongfly.common.base.BaseActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postDelayed({
            MainActivity.start(this)
            finish()
        }, 200L)
    }
}