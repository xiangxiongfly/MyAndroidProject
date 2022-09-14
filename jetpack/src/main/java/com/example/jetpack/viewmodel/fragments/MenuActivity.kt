package com.example.jetpack.viewmodel.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.jetpack.R
import com.xiangxiongfly.common.base.BaseActivity

class MenuActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MenuActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_menu, MenuFragment.newInstance())
            .add(R.id.fl_detail, DetailFragment.newInstance())
            .commit()
    }
}