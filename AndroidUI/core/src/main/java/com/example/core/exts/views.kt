package com.example.core.exts

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

fun View.clipToCircleView() {
    outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline?) {
            view ?: return
            outline?.setOval(0, 0, view.width, view.height)
        }
    }
    // 开启裁剪
    clipToOutline = true
}

fun View.clipToRoundView(radius: Float) {
    outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline?) {
            view ?: return
            outline?.setRoundRect(0, 0, view.width, view.height, radius)
        }
    }
    // 开启裁剪
    clipToOutline = true
}