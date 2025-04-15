package com.xiangxiongfly.androidstatusbar.base

import android.app.Application

class BaseApplication : Application() {
    companion object {
        lateinit var mInstance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

}