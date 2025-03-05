package com.example.base.dialog

import android.content.DialogInterface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.FloatRange
import androidx.annotation.StyleRes

class DialogParams {
    companion object {
        const val NONE_ANIM_STYLE = -1
    }

    lateinit var view: View // Dialog布局

    @FloatRange(from = 0.0, to = 1.0)
    var dimAmount = 0.6F // 背景暗淡度
    var dimEnabled = true // 是否显示背景暗淡度
    var gravity = Gravity.CENTER //显示位置
    var width: Int = ViewGroup.LayoutParams.WRAP_CONTENT // 宽度
    var height: Int = ViewGroup.LayoutParams.WRAP_CONTENT // 高度
    var gap: Int = 0 // 左右间隙
    var cancelable: Boolean = true // 点击返回键是否隐藏
    var canceledOnTouchOutside: Boolean = true // 点击外部区域是否隐藏

    @StyleRes
    var animationStyle: Int = NONE_ANIM_STYLE // 动画样式
    var onShowListener: DialogInterface.OnShowListener? = null // 显示监听
    var onDismissListener: DialogInterface.OnDismissListener? = null // 隐藏监听
}