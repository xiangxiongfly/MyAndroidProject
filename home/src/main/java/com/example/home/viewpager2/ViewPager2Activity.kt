package com.example.home.viewpager2

import android.os.Bundle
import android.view.View
import com.example.home.R
import com.example.home.viewpager2.simple.ViewPager2SimpleActivity
import com.example.home.viewpager2.tab.ViewPager2TabActivity
import com.xiangxiongfly.common.base.BaseActivity

class ViewPager2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)
    }

    fun toViewPager2(v: View) {
        ViewPager2SimpleActivity.start(this)
    }
    fun tpTabVp(v: View) {
        ViewPager2TabActivity.start(this)
    }
}