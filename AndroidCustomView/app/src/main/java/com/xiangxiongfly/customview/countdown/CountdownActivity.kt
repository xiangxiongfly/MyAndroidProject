package com.xiangxiongfly.customview.countdown

import android.os.Bundle
import com.xiangxiongfly.core.base.BaseActivity
import com.xiangxiongfly.core.widgets.countdown.CountdownView
import com.xiangxiongfly.customview.R

class CountdownActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countdown)

        val countdownView = findViewById<CountdownView>(R.id.countdownView)
        countdownView.setOnClickListener {
            countdownView.start()
        }
    }
}