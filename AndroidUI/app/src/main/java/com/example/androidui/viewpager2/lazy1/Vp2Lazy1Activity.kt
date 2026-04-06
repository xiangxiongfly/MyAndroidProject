package com.example.androidui.viewpager2.lazy1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.androidui.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Vp2Lazy1Activity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val titles = listOf("one", "two", "three", "four", "five", "six")
    private val fragments = mutableListOf<Fragment>().apply {
        for (title in titles) {
            add(MyLazyFragment.newInstance(title))
        }
    }

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, Vp2Lazy1Activity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vp2_main)
        initViews()
        setupViewPager2()
    }

    private fun initViews() {
        viewPager2 = findViewById(R.id.viewPager2)
        tabLayout = findViewById(R.id.tabLayout)
    }

    private fun setupViewPager2() {
        val adapter = PageAdapter(this, fragments)
        viewPager2.offscreenPageLimit = 1
        viewPager2.adapter = adapter
        TabLayoutMediator(
            tabLayout,
            viewPager2,
            { tab, position ->
                tab.text = titles[position]
            }
        ).attach()
    }

    class PageAdapter(activity: FragmentActivity, private val fragments: List<Fragment>) :
        FragmentStateAdapter(activity) {
        override fun createFragment(position: Int) = fragments[position]
        override fun getItemCount() = fragments.size
    }
}