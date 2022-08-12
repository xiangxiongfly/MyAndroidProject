package com.xiangxiongfly.common.utils

import android.content.res.Resources

fun dp2px(dpValue: Float): Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (dpValue * scale + 0.5F).toInt()
}

fun dp2px(dpValue: Int): Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (dpValue * scale + 0.5F).toInt()
}

fun Float.dp() = dp2px(this)

fun Int.dp() = dp2px(this)
