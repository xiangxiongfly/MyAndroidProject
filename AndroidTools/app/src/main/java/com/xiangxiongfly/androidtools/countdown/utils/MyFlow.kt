package com.xiangxiongfly.androidtools.countdown.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class MyFlow(
    private val intervalTime: Long,
    private val totalTime: Long,
    private val onTick: (Long) -> Unit,
    private val onFinish: () -> Unit,
    private val scope: CoroutineScope
) {

    private var runType = RunType.INIT
    private val weekOnTick = WeakReference(onTick)
    private val weekOnFinish = WeakReference(onFinish)
    private var job: Job? = null
    private var currentTime = 0L

    fun start() {
        if (runType == RunType.RUNNING) return
        runType = RunType.RUNNING
        job = scope.launch {
            flow {
                currentTime = totalTime
                while (currentTime >= 0) {
                    emit(currentTime)
                    delay(intervalTime)
                    currentTime -= 1000
                }
            }.collect {
                weekOnTick.get()?.invoke(it)
                if (it <= 0) {
                    weekOnFinish.get()?.invoke()
                    runType = RunType.STOP
                }
            }
        }
    }

    fun stop() {
        if (runType != RunType.RUNNING) return
        runType = RunType.STOP
        job?.cancel()
        weekOnFinish.get()?.invoke()
    }

    fun release() {
        job?.cancel()
        job = null
        weekOnTick.clear()
        weekOnFinish.clear()
    }

    enum class RunType {
        INIT, RUNNING, STOP
    }
}