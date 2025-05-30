package com.xiangxiongfly.androidstatusbar

import android.os.Bundle
import android.view.View
import com.xiangxiongfly.androidstatusbar.base.BaseActivity
import com.xiangxiongfly.androidstatusbar.immersion_status_bar.*
import com.xiangxiongfly.androidstatusbar.operate_status_bar.OperateStatusBar1Activity
import com.xiangxiongfly.androidstatusbar.operate_status_bar.OperateStatusBar2Activity
import com.xiangxiongfly.androidstatusbar.operate_status_bar.OperateStatusBar3Activity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_opera_status_bar1 -> OperateStatusBar1Activity.actionStart(mContext)
            R.id.btn_opera_status_bar2 -> OperateStatusBar2Activity.actionStart(mContext)
            R.id.btn_opera_status_bar3 -> OperateStatusBar3Activity.actionStart(mContext)
            R.id.btn_immersion_status_bar1 -> Immersion1Activity.actionStart(mContext)
            R.id.btn_immersion_status_bar2 -> Immersion2Activity.actionStart(mContext)
            R.id.btn_immersion_status_bar3 -> Immersion3Activity.actionStart(mContext)
            R.id.btn_immersion_status_bar4 -> Immersion4Activity.actionStart(mContext)
            R.id.btn_immersion_status_bar5 -> Immersion5Activity.actionStart(mContext)
        }
    }
}