package com.example.jetpack.lifecycle

import android.os.Bundle
import android.view.View
import com.example.jetpack.R
import com.example.jetpack.lifecycle.advertising.AdvertisingActivity
import com.example.jetpack.lifecycle.advertising2.AdvertisingActivity2
import com.example.jetpack.lifecycle.custom.LifecycleCustomActivity
import com.example.jetpack.lifecycle.dialog.LifecycleDialogActivity
import com.example.jetpack.lifecycle.simple.LifecycleSimpleActivity
import com.xiangxiongfly.common.base.BaseActivity

class LifecycleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
    }

    fun toSimple(v: View) {
        LifecycleSimpleActivity.start(this)
    }

    fun toAdvertising1(v: View) {
        AdvertisingActivity.start(this)
    }

    fun toAdvertising2(v: View) {
        AdvertisingActivity2.start(this)
    }

    fun toCustomLifecycle(v: View) {
        LifecycleCustomActivity.start(this)
    }

    fun toDialogActivity(v: View) {
        LifecycleDialogActivity.start(this)
    }
}