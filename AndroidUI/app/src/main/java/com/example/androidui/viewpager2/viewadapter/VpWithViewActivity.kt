package com.example.androidui.viewpager2.viewadapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.androidui.R
import com.example.core.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class VpWithViewActivity : BaseActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    private val items = listOf("One", "Two", "Three", "Four", "Five", "Six", "Seven")

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, VpWithViewActivity::class.java)
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
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager2)
    }

    private fun setupViewPager2() {
        val adapter = MyViewAdapter(items)
        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2, { tab, position ->
            tab.text = items[position]
        }).attach()
    }
}