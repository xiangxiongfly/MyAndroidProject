package com.example.androidui.span

import android.os.Bundle
import android.view.View
import com.example.androidui.R
import com.example.core.base.BaseActivity

class SpannableStringActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spannable_string)
    }

    fun toSimple(v: View) {
        SpanSimpleActivity.start(this)
    }

    fun toTextStyle(v: View) {
        TextStyleActivity.start(this)
    }

    fun toSearch(v: View) {
        SpanSearchActivity.start(this)
    }
}