package com.example.home.viewpager

import android.os.Bundle
import android.view.View
import com.example.home.R
import com.example.home.viewpager.lazy.LazyActivity
import com.example.home.viewpager.lazy.NewLazyActivity
import com.example.home.viewpager.no_scroll_vp.NoScrollVpActivity
import com.example.home.viewpager.simple.ViewPagerSimpleActivity
import com.example.home.viewpager.tab.TabVpActivity
import com.example.home.viewpager.wrap_vp.WrapViewPagerActivity
import com.xiangxiongfly.common.base.BaseActivity

class ViewPagerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
    }

    fun toVpSimple(view: View) {
        ViewPagerSimpleActivity.actionStart(this)
    }

    fun toTabVp(view: View) {
        TabVpActivity.actionStart(this)
    }

    fun toLazy(view: View) {
        LazyActivity.actionStart(this)
    }

    fun toNewLazy(view: View) {
        NewLazyActivity.actionStart(this)
    }

    fun toNoScrollViewPager(view: View) {
        NoScrollVpActivity.actionStart(this)
    }

    fun toWrapViewPager(view: View) {
        WrapViewPagerActivity.actionStart(this)
    }
}
