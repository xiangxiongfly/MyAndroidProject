package com.xiangxiongfly.androidtools.clickarea

import android.os.Bundle
import android.widget.TextView
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.exts.dp
import com.xiangxiongfly.common.exts.expandTouchView
import com.xiangxiongfly.common.utils.ToastUtils

class TouchDelegateActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_delegate)

        val text1 = findViewById<TextView>(R.id.text1)
        val text2 = findViewById<TextView>(R.id.text2)

        text1.run {
            expandTouchView()
            setOnClickListener {
                ToastUtils.show((it as TextView).text.toString())
            }
        }

        text2.run {
            expandTouchView(40.dp)
            setOnClickListener {
                ToastUtils.show((it as TextView).text.toString())
            }
        }
    }
}