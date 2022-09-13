package com.example.jetpack.lifecycle.advertising2

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * 广告管理类
 */
const val TAG = "Lifecycle"

class AdvertisingManage2 : DefaultLifecycleObserver {

    private var mAdvertisingManageListener: AdvertisingManageListener? = null

    private var countDownTimer: CountDownTimer? =
        object : CountDownTimer(5000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                Log.e(TAG, "广告剩余 ${millisUntilFinished / 1000L} 秒")
                mAdvertisingManageListener?.time((millisUntilFinished / 1000L).toInt())
            }

            override fun onFinish() {
                Log.e(TAG, "广告结束，跳转页面")
                mAdvertisingManageListener?.gotoActivity()
            }
        }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        start()
    }

    private fun start() {
        Log.e(TAG, "开始计时")
        countDownTimer?.start()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        cancel()
    }

    /**
     * 结束计时
     */
    private fun cancel() {
        Log.e(TAG, "结束计时")
        countDownTimer?.cancel()
        countDownTimer = null
    }

    fun setAdvertisingManageListener(advertisingManageListener: AdvertisingManageListener) {
        mAdvertisingManageListener = advertisingManageListener
    }

    interface AdvertisingManageListener {
        /**
         * 计时
         */
        fun time(second: Int)

        /**
         * 计时结束后进入主界面
         */
        fun gotoActivity()
    }
}

