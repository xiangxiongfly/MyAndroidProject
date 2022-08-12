package com.example.home.viewpager

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.home.R
import com.example.home.viewpager.simple.ViewPagerSimpleActivity
import com.xiangxiongfly.common.base.BaseActivity

class ViewPagerActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
    }

    fun toVpSimple(v: View) {
        startActivity(Intent(this, ViewPagerSimpleActivity::class.java))
    }
}