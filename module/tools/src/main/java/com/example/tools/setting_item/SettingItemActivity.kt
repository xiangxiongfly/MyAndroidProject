package com.example.tools.setting_item

import android.os.Bundle
import com.example.tools.R
import com.example.tools.setting_item.widgets.SettingItem
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.utils.ToastUtils

class SettingItemActivity : BaseActivity() {
    private lateinit var settingAbout: SettingItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_item)
        initViews()
    }

    private fun initViews() {
        settingAbout = findViewById(R.id.setting_about)
        settingAbout.setOnClickListener {
            ToastUtils.show("关于我们")
        }
    }
}