package com.example.androidui.viewpager2.tab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.androidui.R
import com.example.core.base.BaseActivity
import com.example.core.fragment.TextFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPager2TabActivity : BaseActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    private val titles = arrayListOf("one", "two", "three", "four", "five", "six", "seven", "eight")
    private val fragmentList = arrayListOf<TextFragment>().apply {
        titles.forEachIndexed { index, s ->
            add(TextFragment.newInstance(s))
        }
    }

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, ViewPager2TabActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2_tab)
        initView()
        initViewPager2()
    }

    private fun initView() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager2)
    }

    private fun initViewPager2() {
        // offscreenPageLimit默认不开启预加载
        // offscreenPageLimit设置为1表示缓存前一页，预加载下一页，包含当前页一共三页
        viewPager2.offscreenPageLimit = 1
        viewPager2.adapter = TabAdapter(this, fragmentList)
        TabLayoutMediator(
            tabLayout,
            viewPager2,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = titles[position]
                }
            }
        ).attach()
    }
}