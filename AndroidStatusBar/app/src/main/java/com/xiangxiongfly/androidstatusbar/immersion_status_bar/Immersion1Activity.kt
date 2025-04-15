package com.xiangxiongfly.androidstatusbar.immersion_status_bar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.xiangxiongfly.androidstatusbar.base.BaseActivity
import com.xiangxiongfly.androidstatusbar.R

class Immersion1Activity : BaseActivity() {
    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, Immersion1Activity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_immersion1)
    }
}