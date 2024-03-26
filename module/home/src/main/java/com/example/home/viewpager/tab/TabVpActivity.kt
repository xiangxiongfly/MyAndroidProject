package com.example.home.viewpager.tab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.base.BaseActivity
import com.example.home.R
import com.google.android.material.tabs.TabLayout
import com.xiangxiongfly.common.fragment.TextFragment

class TabVpActivity : BaseActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    private val titles = arrayOf<String>("one", "two", "three", "four", "five", "six")
    private val fragmentList = ArrayList<TextFragment>().apply {
        titles.forEach {
            add(TextFragment.newInstance(it))
        }
    }

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, TabVpActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_vp)
        initView()
        initData()
    }

    private fun initView() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
    }

    private fun initData() {
        val adapter = MyAdapter(supportFragmentManager, titles, fragmentList)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    class MyAdapter(
        fm: FragmentManager,
        val titles: Array<String>,
        val fragmentList: ArrayList<TextFragment>
    ) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return titles.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }
}

