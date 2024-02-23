package com.example.status_bar_demo.immersion_status_bar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.status_bar_demo.R
import com.example.status_bar_demo.base.BaseActivity

class Immersion2Activity : BaseActivity() {
    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, Immersion2Activity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_immersion2)
    }
}