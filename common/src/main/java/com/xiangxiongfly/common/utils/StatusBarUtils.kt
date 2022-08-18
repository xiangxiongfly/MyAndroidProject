package com.xiangxiongfly.common.utils

import android.app.Activity
import android.content.Context
import android.view.View

/**
 * 状态栏工具类
 */
object StatusBarUtils {

    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * 亮色状态栏，图片和文字是黑色的
     */
    fun setLightStatusBar(activity: Activity) {
        val flags = activity.window.decorView.systemUiVisibility
        activity.window.decorView.systemUiVisibility = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    /**
     * 暗色状态栏，图片和文字是白色的
     */
    fun setDarkStatusBar(activity: Activity) {
        val flags =
            activity.window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity.window.decorView.systemUiVisibility =
            flags xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}