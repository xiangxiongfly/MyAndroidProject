package com.example.tools.clickarea

import android.os.Bundle
import android.widget.TextView
import com.example.base.BaseActivity
import com.example.base.utils.ToastUtils
import com.example.tools.R

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