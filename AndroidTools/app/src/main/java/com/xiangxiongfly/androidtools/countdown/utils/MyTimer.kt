package com.xiangxiongfly.androidtools.countdown.utils

import android.os.Handler
import android.os.Looper
import java.lang.ref.WeakReference
import java.util.Timer
import java.util.TimerTask

class MyTimer(
    private val intervalTime: Long,
    private val totalTime: Long,
    onTick: (Long) -> Unit,
    onFinish: () -> Unit
) {

    private val mainHandler = Handler(Looper.getMainLooper())
    private var runType = RunType.INIT
    private val weekOnTick = WeakReference(onTick)
    private val weekOnFinish = WeakReference(onFinish)
    private var timer: Timer? = null
    private var currentTime = 0L

    fun start() {
        if (runType == RunType.RUNNING) return
        runType = RunType.RUNNING
        currentTime = totalTime
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                if (currentTime <= 0) {
                    mainHandler.post {
                        weekOnFinish.get()?.invoke()
                    }
                    cancel()
                    runType = RunType.STOP
                } else {
                    currentTime -= intervalTime
                    mainHandler.post {
                        weekOnTick.get()?.invoke(currentTime)
                    }
                }
            }
        }, 0, intervalTime)
    }

    fun stop() {
        if (runType != RunType.RUNNING) return
        runType = RunType.STOP
        timer?.cancel()
        weekOnFinish.get()?.invoke()
    }

    fun release() {
        timer?.cancel()
        timer = null
        weekOnTick.clear()
        weekOnFinish.clear()
    }

    enum class RunType {
        INIT, RUNNING, STOP
    }
}