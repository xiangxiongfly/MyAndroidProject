package com.example.custom.titlebar

import android.os.Bundle
import android.widget.TextView
import com.example.base.BaseActivity
import com.example.base.utils.ToastUtils
import com.example.custom.R
import com.example.widgets.titlebar.TitleBar

class TitleBarActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_bar)
        val titleBar1 = findViewById<TitleBar>(R.id.titleBar1)
        val titleBar2 = findViewById<TitleBar>(R.id.titleBar2)
        val titleBar3 = findViewById<TitleBar>(R.id.titleBar3)
        val info = findViewById<TextView>(R.id.info)
        val details = findViewById<TextView>(R.id.details)

        titleBar1.setOnLeftClickListener {
            ToastUtils.show("左边")
        }.setOnTitleClickListener {
            ToastUtils.show("标题")
        }.setOnRightClickListener {
            ToastUtils.show("右边")
        }

        titleBar2.setOnLeftClickListener {
            titleBar2.setLeftText("修改左边")
        }.setOnTitleClickListener {
            titleBar2.setTitle("修改标题")
        }.setOnRightClickListener {
            titleBar2.setRightText("修改右边")
        }

        info.setOnClickListener { ToastUtils.show("信息") }
        details.setOnClickListener { ToastUtils.show("详情") }
    }
}