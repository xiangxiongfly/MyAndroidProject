package com.xiangxiongfly.androidtools.countdown

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.androidtools.countdown.fragment.CountDownTimerFragment
import com.xiangxiongfly.androidtools.countdown.fragment.FlowFragment
import com.xiangxiongfly.androidtools.countdown.fragment.HandlerFragment
import com.xiangxiongfly.androidtools.countdown.fragment.TimerFragment
import com.xiangxiongfly.common.base.BaseActivity

class CountDownActivity : BaseActivity() {
    private val titles = listOf("Handler", "CountDownTimer", "Timer", "Flow")
    private val fragments = listOf(
        HandlerFragment.newInstance(),
        CountDownTimerFragment.newInstance(),
        TimerFragment.newInstance(),
        FlowFragment.newInstance()
    )

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_down)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager2)

        viewPager2.adapter = TabAdapter(this, fragments)
        TabLayoutMediator(
            tabLayout,
            viewPager2
        ) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    class TabAdapter(activity: FragmentActivity, val fragments: List<Fragment>) :
        FragmentStateAdapter(activity) {
        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

        override fun getItemCount(): Int {
            return fragments.size
        }
    }
}