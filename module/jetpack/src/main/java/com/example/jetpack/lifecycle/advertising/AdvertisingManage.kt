package com.example.jetpack.lifecycle.advertising

import android.os.CountDownTimer

/**
 * 广告管理类
 */
class AdvertisingManage {

    private var mAdvertisingManageListener: AdvertisingManageListener? = null

    private var countDownTimer: CountDownTimer? =
        object : CountDownTimer(5000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                mAdvertisingManageListener?.time((millisUntilFinished / 1000L).toInt())
            }

            override fun onFinish() {
                mAdvertisingManageListener?.gotoActivity()
            }
        }

    /**
     * 开始计时
     */
    fun start() {
        countDownTimer?.start()
    }

    /**
     * 结束计时
     */
    fun cancel() {
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

