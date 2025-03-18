package com.xiangxiongfly.core.utils

import android.content.res.Resources

fun dp2px(dpValue: Int): Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (dpValue * scale + 0.5F).toInt()
}

fun dp2px(dpValue: Float): Float {
    val scale = Resources.getSystem().displayMetrics.density
    return dpValue * scale + 0.5F
}

fun sp2px(spValue: Int): Int {
    val scale = Resources.getSystem().displayMetrics.scaledDensity
    return (spValue * scale + 0.5F).toInt()
}

fun sp2px(spValue: Float): Float {
    val scale = Resources.getSystem().displayMetrics.scaledDensity
    return spValue * scale + 0.5F
}

val Float.dp
    get() = dp2px(this)

val Int.dp
    get() = dp2px(this)


val Float.sp
    get() = sp2px(this)

val Int.sp
    get() = sp2px(this)