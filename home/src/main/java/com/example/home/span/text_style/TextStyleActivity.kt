package com.example.home.span.text_style

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.widget.TextView
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE

class TextStyleActivity : BaseActivity() {
    private lateinit var textView: TextView

    private var text: String = ""
    private var position: Int = 0

    companion object {
        const val DELAY_TIME = 150L

        fun start(context: Context) {
            context.startActivity(Intent(context, TextStyleActivity::class.java).apply {
                putExtra(KEY_TITLE, "文字跳动效果")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_style)
        textView = findViewById(R.id.textView)

        text = textView.text.toString()
        mHandler.sendEmptyMessageDelayed(0, DELAY_TIME)
    }

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val span = SpannableString(text).apply {
                setSpan(
                    RelativeSizeSpan(1.5F),
                    position,
                    position + 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            textView.text = span
            position++
            if (position >= text.length) {
                position = 0
            }
            this.sendEmptyMessageDelayed(0, DELAY_TIME)
        }
    }
}