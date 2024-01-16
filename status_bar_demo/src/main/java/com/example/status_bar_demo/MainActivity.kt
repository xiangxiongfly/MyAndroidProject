package com.example.status_bar_demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.status_bar_demo.base.BaseActivity
import com.example.status_bar_demo.operate_status_bar.OperateStatusBar1Activity
import com.example.status_bar_demo.operate_status_bar.OperateStatusBar2Activity
import com.example.status_bar_demo.operate_status_bar.OperateStatusBar3Activity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_opera_status_bar1 -> startActivity(
                Intent(
                    mContext,
                    OperateStatusBar1Activity::class.java
                )
            )
            R.id.btn_opera_status_bar2 -> startActivity(
                Intent(
                    mContext,
                    OperateStatusBar2Activity::class.java
                )
            )
            R.id.btn_opera_status_bar3 -> startActivity(
                Intent(
                    mContext,
                    OperateStatusBar3Activity::class.java
                )
            )

        }
    }
}