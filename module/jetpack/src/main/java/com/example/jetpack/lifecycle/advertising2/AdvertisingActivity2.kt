package com.example.jetpack.lifecycle.advertising2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.base.BaseActivity
import com.example.jetpack.R
import com.example.jetpack.NextActivity

class AdvertisingActivity2 : BaseActivity() {
    private lateinit var tvAdvertisingTime: TextView
    private lateinit var btnEnter: Button

    private var advertisingManage: AdvertisingManage2? = null

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AdvertisingActivity2::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertising2)
        initView()
        advertisingManage = AdvertisingManage2()
        lifecycle.addObserver(advertisingManage!!)
        advertisingManage?.setAdvertisingManageListener(
            object : AdvertisingManage2.AdvertisingManageListener {
                override fun time(second: Int) {
                    tvAdvertisingTime.text = "广告剩余 $second 秒"
                }

                override fun gotoActivity() {
                    NextActivity.start(mContext)
                    finish()
                }
            })
        btnEnter.setOnClickListener {
            NextActivity.start(mContext)
            finish()
        }
    }

    private fun initView() {
        tvAdvertisingTime = findViewById(R.id.tv_advertising_time)
        btnEnter = findViewById(R.id.btn_enter)
    }

    override fun onDestroy() {
        super.onDestroy()
        advertisingManage = null
    }
}