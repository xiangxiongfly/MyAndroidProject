package com.example.jetpack.lifecycle.custom

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.jetpack.R
import com.example.jetpack.SecondActivity
import com.example.jetpack.lifecycle.advertising2.AdvertisingManage2

class LifecycleCustomActivity : Activity(), LifecycleOwner {
    private lateinit var tvAdvertisingTime: TextView
    private lateinit var btnEnter: Button

    private lateinit var lifecycleRegistry: LifecycleRegistry

    private var advertisingManage: AdvertisingManage2? = null

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LifecycleCustomActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_custom)
        initView()
        lifecycleRegistry = LifecycleRegistry(this)
        //分发事件到观察者
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        advertisingManage = AdvertisingManage2()
        //注册监听
        lifecycle.addObserver(advertisingManage!!)
        advertisingManage?.setAdvertisingManageListener(
            object : AdvertisingManage2.AdvertisingManageListener {
                override fun time(second: Int) {
                    tvAdvertisingTime.text = "广告剩余 $second 秒"
                }

                override fun gotoActivity() {
                    SecondActivity.start(this@LifecycleCustomActivity)
                    finish()
                }
            })
        btnEnter.setOnClickListener {
            SecondActivity.start(this@LifecycleCustomActivity)
            finish()
        }
    }

    private fun initView() {
        tvAdvertisingTime = findViewById(R.id.tv_advertising_time)
        btnEnter = findViewById(R.id.btn_enter)
    }

    override fun onDestroy() {
        super.onDestroy()
        //分发事件到观察者
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        advertisingManage = null
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}