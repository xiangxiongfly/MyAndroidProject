package com.example.home.viewpager2.tab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.home.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE
import com.xiangxiongfly.common.fragment.TextFragment

class TabActivity : BaseActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    private val titles = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight")
    private val fragmentList = arrayListOf<TextFragment>().apply {
        titles.forEachIndexed { index, s ->
            add(TextFragment.newInstance(s))
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, TabActivity::class.java).apply {
                putExtra(KEY_TITLE, "ViewPager2+TabLayout+Fragment")
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        initView()

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

    private fun initView() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager2)
    }
}