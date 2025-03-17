package com.example.base.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.base.BaseApplication

object KeyboardUtils {
    /**
     * 显示软键盘
     */
    fun showSoftInput(editText: EditText) {
        val imm: InputMethodManager = BaseApplication.getInstance()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        editText.isFocusable = true
        editText.isFocusableInTouchMode = true
        editText.requestFocus()
        imm.showSoftInput(editText, 0)
    }

    /**
     * 隐藏软键盘
     */
    fun hideSoftInput(view: View) {
        val inputMethodManager: InputMethodManager = BaseApplication.getInstance()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * 隐藏软键盘
     */
    fun hideSoftInput(editText: EditText) {
        val inputMethodManager: InputMethodManager = BaseApplication.getInstance()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        editText.clearFocus()
        inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    /**
     * 隐藏软键盘
     */
    fun hideSoftInput(activity: Activity) {
        val window = activity.window
        var view = window.currentFocus
        if (view == null) {
            view = window.decorView
        }
        hideSoftInput(view)
    }

    /**
     * 软键盘切换
     */
    fun toggleSoftInput() {
        val imm: InputMethodManager = BaseApplication.getInstance()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, 0)
    }
}