package com.xiangxiongfly.common.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

const val KEY_TITLE = "title"

open class BaseActivity : AppCompatActivity() {

    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        if (intent.hasExtra(KEY_TITLE)) {
            val title = intent.getStringExtra(KEY_TITLE)
            setTitle(title)
        }
    }
}
