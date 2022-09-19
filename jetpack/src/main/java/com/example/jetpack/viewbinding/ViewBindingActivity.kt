package com.example.jetpack.viewbinding

import android.os.Bundle
import android.view.View
import com.example.jetpack.R
import com.example.jetpack.viewbinding.base.OneActivity
import com.example.jetpack.viewbinding.base2.TwoActivity
import com.example.jetpack.viewbinding.base3.ThreeActivity
import com.example.jetpack.viewbinding.include.VBIncludeActivity
import com.example.jetpack.viewbinding.rv.RvActivity
import com.example.jetpack.viewbinding.simple.VBActivity
import com.example.jetpack.viewbinding.simple.VBFragmentActivity
import com.xiangxiongfly.common.base.BaseActivity

class ViewBindingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_binding)
    }

    fun toSimple(v: View) {
        VBActivity.start(this)
    }

    fun toFragment(v: View) {
        VBFragmentActivity.start(this)
    }

    fun toAdapter(v: View) {
        RvActivity.start(this)
    }

    fun toInclude(v: View) {
        VBIncludeActivity.start(this)
    }

    fun toBase1(v: View) {
        OneActivity.start(this)
    }

    fun toBase2(v: View) {
        TwoActivity.start(this)
    }

    fun toBase3(v: View) {
        ThreeActivity.start(this)
    }
}