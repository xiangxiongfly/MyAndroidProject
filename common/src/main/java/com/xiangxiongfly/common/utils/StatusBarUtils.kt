package com.xiangxiongfly.common.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

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
     * 修改状态栏颜色
     */
    fun setStatusBarColor(activity: Activity, @ColorInt color: Int) {
        activity.window.statusBarColor = color
    }

    /**
     * 修改状态栏颜色，通过资源文件
     */
    fun setStatusBarColor2(activity: Activity, @ColorRes colorId: Int) {
        activity.window.statusBarColor = activity.resources.getColor(colorId)
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

    //刘海屏适配
    fun fitsNotchScreen(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                val lp: WindowManager.LayoutParams = activity.window.getAttributes()
                lp.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                activity.window.attributes = lp
            } catch (e: Exception) {
            }
        }
    }

    /**
     * 隐藏状态栏
     */
    fun hideStatusBar(activity: Activity) {
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    /**
     * 显示状态栏
     */
    fun showStatusBar(activity: Activity) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    /**
     * 操作状态栏
     */
    fun hideBar(activity: Activity, state: BarState) {
        fitsNotchScreen(activity)
        var uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        when (state) {
            BarState.FLAG_HIDE_ALL_BAR ->
                //隐藏状态栏和导航栏
                uiFlags =
                    uiFlags or (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.INVISIBLE)
            BarState.FLAG_HIDE_STATUS_BAR ->
                //隐藏状态栏
                uiFlags = uiFlags or (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.INVISIBLE)
            BarState.FLAG_HIDE_NAVIGATION_BAR ->
                //隐藏导航栏
                uiFlags =
                    uiFlags or (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
            BarState.FLAG_SHOW_ALL_BAR ->
                //显示状态栏和导航栏
                uiFlags = uiFlags or View.SYSTEM_UI_FLAG_VISIBLE
        }
        uiFlags = uiFlags or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        activity.window.decorView.systemUiVisibility = uiFlags
    }
}

enum class BarState {
    /**
     * 隐藏状态栏
     */
    FLAG_HIDE_STATUS_BAR,

    /**
     * 隐藏导航栏
     */
    FLAG_HIDE_NAVIGATION_BAR,

    /**
     * 隐藏状态栏和导航栏
     */
    FLAG_HIDE_ALL_BAR,

    /**
     * 显示状态栏和导航栏
     */
    FLAG_SHOW_ALL_BAR
}
