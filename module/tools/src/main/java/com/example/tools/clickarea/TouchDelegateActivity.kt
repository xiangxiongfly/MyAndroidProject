package com.example.tools.clickarea

import android.os.Bundle
import android.widget.TextView
import com.example.base.BaseActivity
import com.example.base.utils.ToastUtils
import com.example.base.utils.dp
import com.example.base.utils.ext.expandTouchView
import com.example.tools.R

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