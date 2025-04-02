package com.xiangxiongfly.common.utils

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.Runnable
import java.util.concurrent.ConcurrentHashMap

object DebounceThrottleUtils {
    private val handler = Handler(Looper.getMainLooper())
    private val debounceMap = ConcurrentHashMap<String, Runnable>()
    private val throttleMap = ConcurrentHashMap<String, Long>()

    /**
     * 防抖
     *  @param key 唯一标识符（建议使用View ID）
     *  @param delayMillis 防抖时间间隔（毫秒）
     *  @param block 要执行的操作
     */
    fun debounce(key: String, delayMillis: Long, block: () -> Unit) {
        // 移除之前的任务
        debounceMap[key]?.let {
            handler.removeCallbacks(it)
            debounceMap.remove(key)
        }
        // 创建新任务
        val runnable = Runnable {
            block()
        }
        debounceMap.put(key, runnable)
        // 延迟执行任务
        handler.postDelayed(runnable, delayMillis)
    }

    /**
     * 节流处理
     * @param key 唯一标识符（建议使用View ID）
     * @param intervalMillis 节流时间间隔（毫秒）
     * @param block 要执行的操作
     */
    fun throttle(key: String, intervalMillis: Long, block: () -> Unit) {
        val now = System.currentTimeMillis()
        val lastTime = throttleMap[key] ?: 0
        if (now - lastTime >= intervalMillis) {
            throttleMap[key] = now
            block()
        }
    }

    /**
     * 清理指定（在onDestroy中调用）
     */
    fun clear(key: String) {
        debounceMap[key]?.let {
            handler.removeCallbacks(it)
            debounceMap.remove(key)
        }
        throttleMap.remove(key)
    }

    /**
     * 清理全部（在onDestroy中调用）
     */
    fun clearAll() {
        handler.removeCallbacksAndMessages(null)
        debounceMap.clear()
        throttleMap.clear()
    }
}

/**
 * 获取View的key
 */
fun View.getKey(): String {
    return "click_${this.id}"
}

/**
 * 获取EditText的key
 */
fun EditText.getKey(): String {
    return "text_change_${this.id}"
}

/**
 * 防抖扩展方法
 */
fun View.clickDebounce(debounceTime: Long = 500L, action: () -> Unit) {
    val key = getKey()
    setOnClickListener {
        DebounceThrottleUtils.debounce(key, debounceTime, action)
    }
}

/**
 * 节流扩展方法
 */
fun View.clickThrottle(throttleTime: Long = 300L, action: () -> Unit) {
    val key = getKey()
    setOnClickListener {
        DebounceThrottleUtils.throttle(key, throttleTime, action)
    }
}

/**
 * 防抖扩展方法
 */
fun EditText.textChangeDebounce(debounceTime: Long = 300L, action: (Editable?) -> Unit) {
    doAfterTextChanged { editable ->
        val key = getKey()
        DebounceThrottleUtils.debounce(key, debounceTime, {
            action(editable)
        })
    }
}