package com.example.androidui.viewpager

import android.os.Bundle
import android.view.View
import com.example.androidui.R
import com.example.androidui.viewpager.lazy.LazyActivity
import com.example.androidui.viewpager.lazy.NewLazyActivity
import com.example.androidui.viewpager.no_scroll_vp.NoScrollVpActivity
import com.example.androidui.viewpager.simple.ViewPagerSimpleActivity
import com.example.androidui.viewpager.tab.TabVpActivity
import com.example.androidui.viewpager.wrap_vp.WrapViewPagerActivity
import com.example.core.base.BaseActivity

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
