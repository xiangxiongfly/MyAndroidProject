package com.example.jetpack.viewbinding.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.jetpack.R
import com.xiangxiongfly.common.base.BaseActivity

class VBFragmentActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, VBFragmentActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vb_fragment)
    }
}