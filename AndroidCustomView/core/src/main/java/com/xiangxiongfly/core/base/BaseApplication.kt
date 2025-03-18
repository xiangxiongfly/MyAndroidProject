package com.xiangxiongfly.core.base

import android.app.Application

class BaseApplication : Application() {

    companion object {
        lateinit var sInstance: BaseApplication

        @JvmStatic
        fun getInstance(): BaseApplication {
            return sInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
    }
}
