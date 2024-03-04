package com.example.jetpack.viewmodel

import android.os.Bundle
import android.view.View
import com.example.jetpack.R
import com.example.jetpack.viewmodel.fragments.MenuActivity
import com.example.jetpack.viewmodel.simple.ViewModelSimpleActivity
import com.xiangxiongfly.common.base.BaseActivity

class ViewModelActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
    }

    fun toSimple(v: View) {
        ViewModelSimpleActivity.start(this)
    }

    fun toFragments(v: View) {
        MenuActivity.start(this)
    }
}