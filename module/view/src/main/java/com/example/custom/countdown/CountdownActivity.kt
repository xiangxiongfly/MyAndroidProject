package com.example.custom.countdown

import android.os.Bundle
import com.example.base.BaseActivity
import com.example.custom.R
import com.example.widgets.countdown.CountdownView

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