package com.xiangxiongfly.androidtools.countdown.utils

import android.os.Handler
import android.os.Looper
import java.lang.ref.WeakReference

class MyHandler(
    private val intervalTime: Long, // 间隔
    private val totalTime: Long, // 总时长
    onTick: (Long) -> Unit, // 每秒回调
    onFinish: () -> Unit // 结束回调
) {

    private var runType = RunType.INIT
    private var handler: Handler? = Handler(Looper.getMainLooper())
    private val weekOnTick = WeakReference(onTick)
    private val weekOnFinish = WeakReference(onFinish)
    private var currentTime = 0L

    private val runnable = object : Runnable {
        override fun run() {
            if (currentTime > 0) {
                currentTime -= intervalTime
                weekOnTick.get()?.invoke(currentTime)
                handler?.postDelayed(this, intervalTime)
            } else {
                weekOnFinish.get()?.invoke()
                runType = RunType.STOP
            }
        }
    }

    fun start() {
        if (runType == RunType.RUNNING) return
        runType = RunType.RUNNING
        if (currentTime == 0L) {
            currentTime = totalTime
        }
        handler?.post(runnable)
    }

    fun pause() {
        if (runType != RunType.RUNNING) return
        runType = RunType.PAUSE
        handler?.removeCallbacksAndMessages(null)
    }

    fun stop() {
        runType = RunType.STOP
        handler?.removeCallbacksAndMessages(null)
        currentTime = 0
        weekOnFinish.get()?.invoke()
    }

    fun release() {
        runType = RunType.INIT
        handler?.removeCallbacksAndMessages(null)
        handler = null
        weekOnTick.clear()
        weekOnFinish.clear()
    }

    enum class RunType {
        INIT, RUNNING, PAUSE, STOP
    }
}