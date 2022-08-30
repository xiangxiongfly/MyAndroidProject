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

fun dp2px(dpValue: Double): Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (dpValue * scale + 0.5F).toInt()
}

val Float.dp
    get() = dp2px(this)

val Int.dp
    get() = dp2px(this)

val Double.dp
    get() = dp2px(this)
