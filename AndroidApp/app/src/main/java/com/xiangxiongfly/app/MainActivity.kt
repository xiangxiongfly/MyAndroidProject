package com.xiangxiongfly.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.navigation.NavigationBarView
import com.xiangxiongfly.app.databinding.ActivityMainBinding
import com.xiangxiongfly.app.widgets.Tab

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val titles = listOf("首页", "朋友", "聊天", "设置")
    private val fragments = mutableListOf<MyFragment>().apply {
        for (title in titles) {
            add(createFragment(title))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewPager()
        setupNavigationBar()
    }

    private fun setupViewPager() {
        binding.vpMain.adapter = object :
            FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getCount(): Int {
                return fragments.size
            }

            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }
        }
        binding.vpMain.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                binding.navigationBar.setPageScrolled(
                    position,
                    positionOffset,
                    positionOffsetPixels
                )
            }

            override fun onPageSelected(position: Int) {
                binding.navigationBar.scrollTab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun setupNavigationBar() {
        val tabs = mutableListOf<Tab>().apply {
            add(
                Tab(
                    titles[0],
                    R.drawable.tab_home_selected,
                    R.drawable.tab_home_unselect,
                    R.color.tab_selected_color,
                    R.color.tab_unselect_color,
                    0
                )
            )
            add(
                Tab(
                    titles[1],
                    R.drawable.tab_friends_selected,
                    R.drawable.tab_friends_unselect,
                    R.color.tab_selected_color,
                    R.color.tab_unselect_color,
                    1
                )
            )
            add(
                Tab(
                    titles[2],
                    R.drawable.tab_find_selected,
                    R.drawable.tab_find_unselect,
                    R.color.tab_selected_color,
                    R.color.tab_unselect_color,
                    2
                )
            )
            add(
                Tab(
                    titles[3],
                    R.drawable.tab_setting_selected,
                    R.drawable.tab_setting_unselect,
                    R.color.tab_selected_color,
                    R.color.tab_unselect_color,
                    3
                )
            )
        }
        binding.navigationBar.init(tabs)
        binding.navigationBar.setOnItemSelectedClickListener {
            binding.vpMain.currentItem = it
            Log.e("TAG", "点击$it")
        }
        binding.navigationBar.setOnItemReselectedClickListener {
            Log.e("TAG", "重复点击$it")
        }
    }

    private fun createFragment(title: String) = MyFragment.newInstance(title)
}