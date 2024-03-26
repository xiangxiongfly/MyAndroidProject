package com.example.base.utils

import android.view.View

/**
 * EditText工具类
 */
object EditTextUtils {
    fun requestFocus(v: View) {
        v.isFocusable = true
        v.isFocusableInTouchMode = true
        v.requestFocus()
    }
}