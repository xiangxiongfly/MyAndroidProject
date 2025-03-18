package com.xiangxiongfly.core.widgets.titlebar

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

object TitleBarFactory {

    /**
     * 创建中间的标题
     */
    fun newTitleView(context: Context): TextView {
        return TextView(context).apply {
            gravity = Gravity.CENTER_VERTICAL
            isFocusable = true
            setSingleLine()
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
            )
        }
    }

    /**
     * 创建左边
     */
    fun newLeftView(context: Context): TextView {
        return TextView(context).apply {
            gravity = Gravity.CENTER_VERTICAL
            isFocusable = true
            setSingleLine()
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.MATCH_PARENT,
                Gravity.START or Gravity.CENTER_VERTICAL
            )
        }
    }

    /**
     * 创建右边
     */
    fun newRightView(context: Context): TextView {
        return TextView(context).apply {
            gravity = Gravity.CENTER_VERTICAL
            isFocusable = true
            setSingleLine()
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.MATCH_PARENT,
                Gravity.END or Gravity.CENTER_VERTICAL
            )
        }
    }

    /**
     * 创建下划线
     */
    fun newLine(context: Context): View {
        return View(context).apply {
            val lp = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                1,
                Gravity.BOTTOM
            )
            layoutParams = lp
        }
    }
}