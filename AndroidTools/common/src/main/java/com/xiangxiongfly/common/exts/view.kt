package com.xiangxiongfly.common.exts

import android.graphics.Rect
import android.util.Log
import android.view.TouchDelegate
import android.view.View

/**
 * 扩大点击区域
 * 必须保证targetView有父View
 */
fun View.expandTouchView(expandSize: Int = 15.dp) {
    val parentView = parent as? View
    parentView?.post {
        val rect = Rect()
        getHitRect(rect)
        Log.e("TAG", "rect = $rect")
        rect.left -= expandSize
        rect.top -= expandSize
        rect.right += expandSize
        rect.bottom += expandSize
        Log.e("TAG", "expandRect = $rect")
        parentView.touchDelegate = TouchDelegate(rect, this)
    }
}