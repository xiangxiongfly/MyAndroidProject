package com.example.core.manager

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.collection.ArrayMap

class ActivityManager private constructor() : Application.ActivityLifecycleCallbacks {

    private lateinit var application: Application
    private val activitySet = ArrayMap<String, Activity>() // Activity集合
    var topActivity: Activity? = null // 栈顶Activity
        private set
    var resumedActivity: Activity? = null // 前台可见Activity
        private set
    private val lifecycleCallbacks = ArrayList<AppLifecycleCallback>() // 生命周期回调集合

    companion object {
        private var instance: ActivityManager? = null

        @JvmStatic
        @Synchronized
        fun getInstance(): ActivityManager {
            if (instance == null) {
                instance = ActivityManager()
            }
            return instance!!
        }
    }

    fun init(app: Application) {
        application = app
        application.registerActivityLifecycleCallbacks(this)
    }

    private fun getTag(obj: Any): String {
        return obj.javaClass.name + Integer.toHexString(obj.hashCode())
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activitySet[getTag(activity)] = activity
        topActivity = activity
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
        if (topActivity == activity && resumedActivity == null) {
            for (callback in lifecycleCallbacks) {
                callback.onApplicationForeground(activity)
            }
        }
        topActivity = activity
        resumedActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
        if (resumedActivity == activity) {
            resumedActivity = null
        }
        if (resumedActivity == null) {
            for (callback in lifecycleCallbacks) {
                callback.onApplicationBackground(activity)
            }
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        activitySet.remove(getTag(activity))
        if (topActivity == activity) {
            topActivity = null
        }
    }

    /**
     * 判断App是否在前台
     */
    fun isForeground(): Boolean {
        return resumedActivity != null
    }

    /**
     * 注册生命周期回调
     */
    fun registerAppLifecycleCallback(callback: AppLifecycleCallback) {
        lifecycleCallbacks.add(callback)
    }

    /**
     * 取消注册生命周期回调
     */
    fun unregisterAppLifecycleCallback(callback: AppLifecycleCallback) {
        lifecycleCallbacks.remove(callback)
    }

    interface AppLifecycleCallback {
        /**
         * App从后台进入前台
         */
        fun onApplicationForeground(activity: Activity)

        /**
         * App从前台进入后台
         */
        fun onApplicationBackground(activity: Activity)
    }
}