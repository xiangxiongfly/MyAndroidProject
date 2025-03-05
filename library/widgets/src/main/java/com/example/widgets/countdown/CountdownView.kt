package com.example.widgets.countdown

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.example.base.utils.dp
import com.example.widgets.R

class CountdownView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr), Runnable {

    private var totalSecond = 10
    private var currentSecond = 0
    private var TIMING_FINISHED = "发送验证码"
    private val TIMING_TEXT = "已发送(%d)"

    init {
        text = TIMING_FINISHED
        gravity = Gravity.CENTER
        minWidth = 80.dp
        textSize = 13F
        setPadding(10.dp)
        setTextColor(Color.parseColor("#FFFFFF"))
        background = ContextCompat.getDrawable(context, R.drawable.selector_countdown)
    }

    /**
     * 开始倒计时
     */
    fun start() {
        isEnabled = false
        currentSecond = totalSecond
        post(this)
    }

    /**
     * 结束倒计时
     */
    fun cancel() {
        currentSecond = 0
        text = TIMING_FINISHED
        isEnabled = true
    }

    override fun run() {
        if (currentSecond == 0) {
            cancel()
            return
        }
        currentSecond--
        text = String.format(TIMING_TEXT, currentSecond)
        postDelayed(this, 1000)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeCallbacks(this)
    }
}