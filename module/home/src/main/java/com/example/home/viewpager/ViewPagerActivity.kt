package com.example.home.viewpager

import android.os.Bundle
import android.view.View
import com.example.home.R
import com.example.home.viewpager.lazy.LazyActivity
import com.example.home.viewpager.lazy.NewLazyActivity
import com.example.home.viewpager.simple.ViewPagerSimpleActivity
import com.example.home.viewpager.tab.TabVpActivity
import com.xiangxiongfly.common.base.BaseActivity

class ViewPagerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
    }

    fun toVpSimple(v: View) {
        ViewPagerSimpleActivity.start(this)
    }

    fun toTabVp(v: View) {
        TabVpActivity.start(this)
    }

    fun toLazy(v: View) {
        LazyActivity.start(this)
    }

    fun toNewLazy(v: View) {
        NewLazyActivity.start(this)
    }
}
