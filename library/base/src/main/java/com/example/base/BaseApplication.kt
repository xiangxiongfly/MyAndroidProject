package com.example.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.base.manager.ActivityManager

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
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())
        ActivityManager.getInstance().init(this)
    }
}

class ApplicationLifecycleObserver : DefaultLifecycleObserver {

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        onAppForeground()
    }

    fun onAppForeground() {
        Log.e("TAG", "App在前台")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        onAppBackground()
    }

    fun onAppBackground() {
        Log.e("TAG", "App在后台")
    }
}