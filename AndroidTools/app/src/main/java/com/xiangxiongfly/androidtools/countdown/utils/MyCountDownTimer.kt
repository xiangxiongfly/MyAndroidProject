package com.xiangxiongfly.androidtools.countdown.utils

import android.os.CountDownTimer
import java.lang.ref.WeakReference

class MyCountDownTimer(
    private val intervalTime: Long,
    private val totalTime: Long,
    onTick: (Long) -> Unit,
    onFinish: () -> Unit
) {

    private var runType = RunType.INIT
    private val weekOnTick = WeakReference(onTick)
    private val weekOnFinish = WeakReference(onFinish)
    private var countDownTimer: CountDownTimer? = null

    fun start() {
        if (runType == RunType.RUNNING) return
        runType = RunType.RUNNING
        countDownTimer = object : CountDownTimer(totalTime, intervalTime) {
            override fun onTick(p0: Long) {
                weekOnTick.get()?.invoke(p0)
            }

            override fun onFinish() {
                weekOnFinish.get()?.invoke()
                runType = RunType.STOP
            }
        }
        countDownTimer!!.start()
    }

    fun stop() {
        runType = RunType.STOP
        countDownTimer?.cancel()
        weekOnFinish.get()?.invoke()
    }

    fun release() {
        countDownTimer?.cancel()
        countDownTimer = null
        weekOnTick.clear()
        weekOnFinish.clear()
    }

    enum class RunType {
        INIT, RUNNING, STOP
    }
}