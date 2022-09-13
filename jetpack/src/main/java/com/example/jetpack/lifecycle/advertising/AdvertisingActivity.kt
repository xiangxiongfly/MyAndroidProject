package com.example.jetpack.lifecycle.advertising

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.jetpack.R
import com.example.jetpack.SecondActivity
import com.xiangxiongfly.common.base.BaseActivity

class AdvertisingActivity : BaseActivity() {
    private lateinit var tvAdvertisingTime: TextView
    private lateinit var btnEnter: Button

    private var advertisingManage: AdvertisingManage? = null

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AdvertisingActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertising1)
        initView()
        advertisingManage = AdvertisingManage()
        advertisingManage?.setAdvertisingManageListener(
            object : AdvertisingManage.AdvertisingManageListener {
                override fun time(second: Int) {
                    tvAdvertisingTime.text = "广告剩余 $second 秒"
                }

                override fun gotoActivity() {
                    SecondActivity.start(mContext)
                    finish()
                }
            })
        btnEnter.setOnClickListener {
            SecondActivity.start(mContext)
            finish()
        }
        advertisingManage?.start()
    }

    private fun initView() {
        tvAdvertisingTime = findViewById(R.id.tv_advertising_time)
        btnEnter = findViewById(R.id.btn_enter)
    }

    override fun onDestroy() {
        super.onDestroy()
        advertisingManage?.cancel()
        advertisingManage = null
    }
}