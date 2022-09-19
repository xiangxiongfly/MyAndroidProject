package com.example.jetpack.lifecycle.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import com.example.jetpack.R
import com.example.jetpack.LIFECYCLE
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE

class LifecycleSimpleActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LifecycleSimpleActivity::class.java).apply {
                putExtra(KEY_TITLE, "Lifecycle简单使用")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_simple)
        lifecycle.addObserver(MyObserver())
        lifecycle.addObserver(MyObserver2())
        Log.e(LIFECYCLE, "Activity onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e(LIFECYCLE, "Activity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(LIFECYCLE, "Activity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(LIFECYCLE, "Activity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(LIFECYCLE, "Activity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(LIFECYCLE, "Activity onDestroy")
    }
}

//创建观察者方式一：
class MyObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onInit() {
        Log.e(LIFECYCLE, "MyObserver 初始化")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onConnect(owner: LifecycleOwner) {
        Log.e(LIFECYCLE, "MyObserver 建立连接")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onDisconnect(owner: LifecycleOwner) {
        Log.e(LIFECYCLE, "MyObserver 断开连接")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onRelease() {
        Log.e(LIFECYCLE, "MyObserver 释放")
    }
}

//创建观察者方式二
class MyObserver2 : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        onInit()
    }

    private fun onInit() {
        Log.e(LIFECYCLE, "MyObserver2 初始化")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        onConnect(owner)
    }

    private fun onConnect(owner: LifecycleOwner) {
        Log.e(LIFECYCLE, "MyObserver2 建立连接")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        onDisconnect(owner)
    }

    private fun onDisconnect(owner: LifecycleOwner) {
        Log.e(LIFECYCLE, "MyObserver2 断开连接")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        onRelease()
    }

    private fun onRelease() {
        Log.e(LIFECYCLE, "MyObserver2 释放")
    }
}

class MyLifecycleOwner : LifecycleOwner {
    private val mLifecycle = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle {
        return mLifecycle
    }

    fun create() {
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    fun start() {
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    fun stop() {
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }
}