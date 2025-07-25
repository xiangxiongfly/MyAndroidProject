package com.xiangxiongfly.androidstatusbar.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


/**
 * 状态栏工具类
 */
object BarUtils {

    /**
     * 适配刘海屏
     */
    fun fitsNotchScreen(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val lp: WindowManager.LayoutParams = activity.window.attributes
            lp.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            activity.window.attributes = lp
        }
    }

    /**
     * 隐藏ActionBar
     */
    fun setNoActionBar(activity: AppCompatActivity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE) // 隐藏标题栏
        activity.supportActionBar?.hide() // 隐藏ActionBar
    }

    /**
     * 全屏显示
     */
    fun fullScreen(activity: AppCompatActivity) {
        // 设置透明状态栏
        activity.window.statusBarColor = Color.TRANSPARENT
        // 内容延伸至状态栏
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        // 内容延伸至导航栏
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        // 隐藏状态栏
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // 隐藏导航栏
        activity.window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    /**
     * 全屏显示，状态栏显示文字图片
     */
    fun fullScreen2(activity: AppCompatActivity) {
        // 设置透明状态栏
        activity.window.statusBarColor = Color.TRANSPARENT

        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        // 隐藏导航栏
        activity.window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
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
     * 隐藏底部导航栏
     */
    fun hideNavigationBar(activity: Activity) {
        activity.window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    /**
     * 显示底部导航栏
     */
    fun showNavigationBar(activity: Activity) {
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }

    /**
     * 修改状态栏颜色
     */
    fun setStatusBarColor(activity: Activity, @ColorInt color: Int) {
        activity.window.statusBarColor = color
    }

    /**
     * 修改状态栏颜色，通过Color资源
     */
    fun setStatusBarColorRes(activity: Activity, @ColorRes colorResId: Int) {
        setStatusBarColor(activity, ContextCompat.getColor(activity, colorResId))
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

    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }

    /**
     * 获取状态栏高度
     */
    fun getNavigationBarHeight(context: Context): Int {
        val resourceId: Int =
            context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }

    /**
     * 是否有导航栏
     */
    fun isNavigationBar(activity: Activity): Boolean {
        val vp = activity.window.decorView as? ViewGroup
        if (vp != null) {
            for (i in 0 until vp.childCount) {
                vp.getChildAt(i).context.packageName
                if (vp.getChildAt(i).id != -1 && "navigationBarBackground" ==
                    activity.resources.getResourceEntryName(vp.getChildAt(i).id)
                ) return true
            }
        }
        return false
    }
}

