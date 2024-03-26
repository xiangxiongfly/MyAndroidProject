package com.example.base.action

import android.os.Handler
import android.os.Looper
import android.os.SystemClock

interface HandlerAction {
    companion object {
        val HANDLER = Handler(Looper.getMainLooper())
    }

    /**
     * 获取Handler对象
     */
    fun getHandler() = HANDLER

    /**
     * 立即执行任务
     */
    fun post(runnable: Runnable): Boolean {
        return postDelayed(runnable, 0)
    }

    /**
     * 延迟执行任务
     */
    fun postDelayed(runnable: Runnable, delayMillis: Long): Boolean {
        return postAtTime(
            runnable,
            SystemClock.uptimeMillis() + if (delayMillis < 0) 0 else delayMillis
        )
    }

    /**
     * 指定时间执行任务
     */
    fun postAtTime(runnable: Runnable, uptimeMillis: Long): Boolean {
        // 发送和当前对象相关的消息回调
        return HANDLER.postAtTime(runnable, this, uptimeMillis)
    }

    /**
     * 移除消息
     */
    fun removeCallbacks(runnable: Runnable) {
        HANDLER.removeCallbacks(runnable)
    }

    /**
     * 移除全部消息
     */
    fun removeCallbacks() {
        HANDLER.removeCallbacksAndMessages(this)
    }
}