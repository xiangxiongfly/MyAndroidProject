package com.example.home.span

import android.os.Bundle
import android.view.View
import com.example.base.BaseActivity
import com.example.home.R

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