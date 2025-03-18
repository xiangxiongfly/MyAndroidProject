package com.xiangxiongfly.customview.setting_item

import android.os.Bundle
import com.xiangxiongfly.core.base.BaseActivity
import com.xiangxiongfly.core.utils.ToastUtils
import com.xiangxiongfly.core.widgets.settingitem.SettingItem
import com.xiangxiongfly.customview.R

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