package com.xiangxiongfly.myandroid

import android.os.Bundle
import com.example.base.BaseActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postDelayed({
            MainActivity.start(this)
            finish()
        }, 800L)
    }
}