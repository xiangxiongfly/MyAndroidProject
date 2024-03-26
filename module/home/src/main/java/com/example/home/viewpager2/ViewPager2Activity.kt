package com.example.home.viewpager2

import android.os.Bundle
import android.view.View
import com.example.base.BaseActivity
import com.example.home.R
import com.example.home.viewpager2.simple.ViewPager2SimpleActivity
import com.example.home.viewpager2.tab.ViewPager2TabActivity

class ViewPager2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)
    }

    fun toViewPager2(view: View) {
        ViewPager2SimpleActivity.actionStart(this)
    }

    fun tpTabVp(view: View) {
        ViewPager2TabActivity.actionStart(this)
    }
}