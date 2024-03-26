package com.example.base.utils.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager

/**
 * 网络工具帮助类
 */
object NetworkHelper {

    private var currentNetworkType: Int = -1
    private val mListeners = hashSetOf<OnNetworkStateChangedListener>()

    /**
     * 注册监听
     */
    fun registerListener(context: Context, listener: OnNetworkStateChangedListener) {
        val preSize = mListeners.size
        mListeners.add(listener)
        // 防止重复注册
        if (preSize == 0 && mListeners.size == 1) {
            val mFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            context.registerReceiver(NetworkStateChangedReceiver, mFilter)
        }
    }

    /**
     * 是否已注册
     */
    fun isRegistered(listener: OnNetworkStateChangedListener): Boolean {
        return mListeners.contains(listener)
    }

    /**
     * 取消注册
     */
    fun unregisterListener(context: Context, listener: OnNetworkStateChangedListener) {
        val preSize = mListeners.size
        mListeners.remove(listener)
        // 防止重复注销
        if (preSize == 1 && mListeners.size == 0) {
            context.unregisterReceiver(NetworkStateChangedReceiver)
        }
    }

    /**
     * 网络状态改变广播
     */
    object NetworkStateChangedReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
                val netWorkType = NetworkUtils.getNetWorkType(context)
                if (currentNetworkType == netWorkType) {
                    return
                }
                currentNetworkType = netWorkType
                if (netWorkType == NetworkUtils.NETWORK_NONE) {
                    for (listener in mListeners) {
                        listener.onDisconnected()
                    }
                } else {
                    for (listener in mListeners) {
                        listener.onConnected(currentNetworkType)
                    }
                }
            }
        }
    }

    interface OnNetworkStateChangedListener {
        fun onDisconnected()
        fun onConnected(networkType: Int)
    }
}