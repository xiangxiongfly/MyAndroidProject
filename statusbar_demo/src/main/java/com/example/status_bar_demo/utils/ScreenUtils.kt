package com.example.status_bar_demo.utils

import android.content.Context

/**
 * 屏幕工具类
 */
object ScreenUtils {

    /**
     * 获取屏幕宽度
     */
    fun getScreenWidth(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.heightPixels
    }
}