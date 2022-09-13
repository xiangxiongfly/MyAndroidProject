package com.example.jetpack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.xiangxiongfly.common.base.BaseActivity

class SecondActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SecondActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}