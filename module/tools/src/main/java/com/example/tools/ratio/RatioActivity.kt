package com.example.tools.ratio

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.base.BaseActivity
import com.example.tools.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class RatioActivity : BaseActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private val titles = arrayOf("adjustViewBounds", "百分比布局", "ConstraintLayout", "自定义View")
    private val fragments = arrayOf(
        AvbFragment.newInstance(),
        PercentFragment.newInstance(),
        ConstraintLayoutFragment.newInstance(),
        RatioViewFragment.newInstance()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ratio)
        initViews()
        initData()
    }

    private fun initViews() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager2)
    }

    private fun initData() {
        viewPager.adapter = MyAdapter(this, fragments)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }
}

class MyAdapter(
    fragmentActivity: FragmentActivity,
    private val fragmentList: Array<Fragment>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = fragmentList.size
    override fun createFragment(position: Int): Fragment = fragmentList[position]
}