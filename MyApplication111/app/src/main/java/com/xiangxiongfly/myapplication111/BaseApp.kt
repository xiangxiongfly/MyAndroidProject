package com.xiangxiongfly.myapplication111

import android.app.Application

class BaseApp : Application() {

    companion object {
        private lateinit var instance: BaseApp
        fun getInstance(): BaseApp {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
