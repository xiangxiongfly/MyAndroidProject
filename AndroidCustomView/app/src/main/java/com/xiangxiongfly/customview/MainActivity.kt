package com.xiangxiongfly.customview

import android.os.Bundle
import com.google.android.flexbox.FlexboxLayout
import com.xiangxiongfly.core.base.BaseActivity
import com.xiangxiongfly.core.exts.addElement
import com.xiangxiongfly.customview.bubble.BubbleImageViewActivity
import com.xiangxiongfly.customview.contacts.ContactsActivity
import com.xiangxiongfly.customview.countdown.CountdownActivity
import com.xiangxiongfly.customview.customedittext.MyEditTextActivity
import com.xiangxiongfly.customview.navigationbar.NavigationBarActivity
import com.xiangxiongfly.customview.setting_item.SettingItemActivity
import com.xiangxiongfly.customview.statelayout.StateLayoutActivity
import com.xiangxiongfly.customview.titlebar.TitleBarActivity

class MainActivity : BaseActivity() {
    private lateinit var flexboxLayout: FlexboxLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        addElements()
    }

    private fun initViews() {
        flexboxLayout = findViewById(R.id.flexboxLayout)
    }

    private fun addElements() {
        flexboxLayout.removeAllViews()
        flexboxLayout.addElement(context, "SettingItem设置条", SettingItemActivity::class.java)
        flexboxLayout.addElement(context, "StateLayout状态页", StateLayoutActivity::class.java)
        flexboxLayout.addElement(context, "TitleBar标题栏", TitleBarActivity::class.java)
        flexboxLayout.addElement(context, "自定义EditText", MyEditTextActivity::class.java)
        flexboxLayout.addElement(context, "倒计时", CountdownActivity::class.java)
        flexboxLayout.addElement(context, "底部导航栏", NavigationBarActivity::class.java)
        flexboxLayout.addElement(context, "通讯录(侧边索引栏)", ContactsActivity::class.java)
        flexboxLayout.addElement(context, "气泡ImageView", BubbleImageViewActivity::class.java)
    }
}