package com.example.androidui.viewpager2

import android.os.Bundle
import android.view.View
import com.example.androidui.R
import com.example.androidui.viewpager2.fragmentadapter.VpWithFragmentActivity
import com.example.androidui.viewpager2.lazy1.Vp2Lazy1Activity
import com.example.androidui.viewpager2.viewadapter.VpWithViewActivity
import com.example.core.base.BaseActivity

class ViewPager2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)
    }

    fun toViewAdapter(view: View) {
        VpWithViewActivity.actionStart(this)
    }

    fun toFragmentAdapter(view: View) {
        VpWithFragmentActivity.actionStart(this)
    }

    fun toVp2Lazy1(view: View) {
        Vp2Lazy1Activity.actionStart(this)
    }
}