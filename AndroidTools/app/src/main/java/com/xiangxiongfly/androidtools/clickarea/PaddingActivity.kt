package com.xiangxiongfly.androidtools.clickarea

import android.os.Bundle
import android.widget.TextView
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.utils.ToastUtils

class PaddingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_padding)

        findViewById<TextView>(R.id.text1).setOnClickListener {
            ToastUtils.show((it as TextView).text.toString())
        }
        findViewById<TextView>(R.id.text2).setOnClickListener {
            ToastUtils.show((it as TextView).text.toString())
        }
    }
}