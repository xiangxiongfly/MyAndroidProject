package com.example.androidui

import android.content.Intent
import android.os.Bundle
import com.example.core.base.BaseActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postDelayed({
            context.startActivity(Intent(context, MainActivity::class.java))
            finish()
        }, 2000L)
    }
}