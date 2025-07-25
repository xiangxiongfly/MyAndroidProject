package com.xiangxiongfly.androidstatusbar.exts

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T : Activity> Context.actionStart() {
    Intent(this, T::class.java).apply {
        startActivity(this)
    }
}