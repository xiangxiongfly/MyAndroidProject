package com.example.home.span

import android.os.Bundle
import android.view.View
import com.example.home.R
import com.example.home.span.text_style.TextStyleActivity
import com.example.home.span.simple.SpanSimpleActivity
import com.xiangxiongfly.common.base.BaseActivity

class SpannableStringActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spannable_string)
    }

    fun toSimple(v: View) {
        SpanSimpleActivity.start(this)
    }

    fun toOther(v: View) {
        TextStyleActivity.start(this)
    }
}