package com.example.androidui.viewpager2.fragmentadapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.androidui.R
import com.example.core.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class VpWithFragmentActivity : BaseActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout

    private val titles = listOf("One", "Two", "Three", "Four", "Five", "Six", "Seven")
    private val fragments = mutableListOf<Fragment>().apply {
        for (title in titles) {
            add(PageFragment.newInstance(title))
        }
    }

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, VpWithFragmentActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vp2_main)
        initView()
        setupViewPager2()
    }

    private fun initView() {
        viewPager2 = findViewById(R.id.viewPager2)
        tabLayout = findViewById(R.id.tabLayout)
    }

    private fun setupViewPager2() {
        val adapter = MyFragmentAdapter(this, fragments)
        viewPager2.adapter = adapter
        TabLayoutMediator(
            tabLayout,
            viewPager2,
            { tab, position ->
                tab.text = titles[position]
            }
        ).attach()

        // 设置监听
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("ViewPager2", "当前第 ${position} 页")
            }
        })
    }
}