package com.example.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.BaseFragment
import com.example.custom.countdown.CountdownActivity
import com.example.custom.my_edittext.MyEditTextActivity
import com.example.custom.setting_item.SettingItemActivity
import com.example.custom.statelayout.StateLayoutActivity
import com.example.custom.titlebar.TitleBarActivity
import com.google.android.flexbox.FlexboxLayout
import com.xiangxiongfly.common.exts.addElement

class CustomFragment : BaseFragment() {
    private lateinit var flexboxLayout: FlexboxLayout

    companion object {
        @JvmStatic
        fun newInstance(title: String) = CustomFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_custom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        addElements()
    }

    private fun initViews(view: View) {
        flexboxLayout = view.findViewById(R.id.flexboxLayout)
    }

    private fun addElements() {
        flexboxLayout.removeAllViews()
        flexboxLayout.addElement(mContext, "SettingItem设置条", SettingItemActivity::class.java)
        flexboxLayout.addElement(mContext, "StateLayout状态页", StateLayoutActivity::class.java)
        flexboxLayout.addElement(mContext, "TitleBar标题栏", TitleBarActivity::class.java)
        flexboxLayout.addElement(mContext, "自定义EditText", MyEditTextActivity::class.java)
        flexboxLayout.addElement(mContext, "倒计时", CountdownActivity::class.java)
    }
}