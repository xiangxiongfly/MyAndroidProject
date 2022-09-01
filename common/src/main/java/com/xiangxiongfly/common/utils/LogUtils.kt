package com.xiangxiongfly.common.utils

import android.util.Log
import com.xiangxiongfly.common.BuildConfig

const val DEFAULT_TAG = "MyAndroid"

object LogUtils {
    fun e(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg)
        }
    }
}

fun logE(msg: String) {
    LogUtils.e(DEFAULT_TAG, msg)
}

fun logI(msg: String) {
    LogUtils.i(DEFAULT_TAG, msg)
}

fun logD(msg: String) {
    LogUtils.d(DEFAULT_TAG, msg)
}