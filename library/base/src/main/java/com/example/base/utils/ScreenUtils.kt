package com.example.base.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.WindowManager

/**
 * 屏幕工具类
 */
object ScreenUtils {

    /**
     * 获取屏幕宽度
     */
    fun getScreenWidth(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels
    }

    /**
     * 获取屏幕高度
     */
    fun getScreenHeight(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.heightPixels
    }

    /**
     * 设置全屏
     * 在setContentView之前设置
     */
    fun setFullScreen(activity: Activity) {
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    /**
     * 获取屏幕方向
     */
    fun getScreenOrientation(context: Context): Int {
        val orientation = context.resources.configuration.orientation
        return if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            //竖屏
            Configuration.ORIENTATION_PORTRAIT
        } else {
            //横屏
            Configuration.ORIENTATION_LANDSCAPE
        }
    }
}