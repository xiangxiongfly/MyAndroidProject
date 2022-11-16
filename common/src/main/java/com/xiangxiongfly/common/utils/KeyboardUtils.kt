package com.xiangxiongfly.common.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardUtils {
    /**
     * 显示软键盘，需要先 requestFocus 获取焦点，如果是在 Activity Create，那么需要延迟一段时间
     */
    fun showKeyboard(view: View?) {
        view ?: return
        val manager: InputMethodManager =
            (view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                ?: return) as InputMethodManager
        manager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    /**
     * 隐藏软键盘
     */
    fun hideKeyboard(view: View?) {
        view ?: return
        val manager: InputMethodManager =
            (view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                ?: return) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    /**
     * 切换软键盘
     */
    fun toggleSoftInput(view: View?) {
        view ?: return
        val manager: InputMethodManager =
            (view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                ?: return) as InputMethodManager
        manager.toggleSoftInput(0, 0)
    }
}