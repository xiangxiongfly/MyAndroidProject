package com.example.jetpack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.BaseActivity

class NextActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, NextActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
    }
}