package com.xiangxiongfly.myandroid

import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.widget.navigation.BottomNavigation
import com.xiangxiongfly.common.widget.navigation.TabItem

const val KEY_POSITION: String = "key_position"

class MainActivity : BaseActivity() {
    private lateinit var vpMain: ViewPager
    private lateinit var bottomNavigation: BottomNavigation

    private var currentPosition = 0

    private val mTitles = arrayOf("首页", "朋友圈", "发现", "设置")
    private val mFragments = SparseArray<TabFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            currentPosition = savedInstanceState.getInt(KEY_POSITION, 0)
        }

        vpMain = findViewById(R.id.vp_main)
        bottomNavigation = findViewById(R.id.bottom_navigation)

        val list = ArrayList<TabItem>().apply {
            add(
                TabItem(
                    R.drawable.tab_home_selected,
                    R.drawable.tab_home_unselect,
                    mTitles[0]
                )
            )
            add(
                TabItem(
                    R.drawable.tab_friends_selected,
                    R.drawable.tab_friends_unselect,
                    mTitles[1]
                )
            )
            add(
                TabItem(
                    R.drawable.tab_find_selected,
                    R.drawable.tab_find_unselect,
                    mTitles[2]
                )
            )
            add(
                TabItem(
                    R.drawable.tab_setting_selected,
                    R.drawable.tab_setting_unselect,
                    mTitles[3]
                )
            )
        }

        bottomNavigation.init(list)
        bottomNavigation.setCurrentTab(currentPosition)
        bottomNavigation.setOnItemSelectedListener(object :
            BottomNavigation.OnItemSelectedListener {
            override fun onItemSelected(tabItem: TabItem) {
                currentPosition = tabItem.index
                vpMain.setCurrentItem(currentPosition, false)
            }
        })
        bottomNavigation.setOnItemReselectedListener(object :
            BottomNavigation.OnItemReselectedListener {
            override fun onItemReselected(tabItem: TabItem) {
                when (tabItem.index) {
                    0 -> Log.e("TAG", "首页2")
                    1 -> Log.e("TAG", "朋友圈2")
                    2 -> Log.e("TAG", "发现2")
                    3 -> Log.e("TAG", "设置2")
                }
            }
        })

        initViewPager()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_POSITION, currentPosition)
        super.onSaveInstanceState(outState)
    }

    private fun initViewPager() {
        vpMain.offscreenPageLimit = mTitles.size
        vpMain.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return mTitles.size
            }

            override fun getItem(position: Int): Fragment {
                return TabFragment.newInstance(mTitles[position])
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val tabFragment = super.instantiateItem(container, position) as TabFragment
                mFragments.put(position, tabFragment)
                return tabFragment
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                mFragments.remove(position)
                super.destroyItem(container, position, `object`)
            }
        }
        vpMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                bottomNavigation.setPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                currentPosition = position
                bottomNavigation.setCurrentTab(currentPosition)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }
}

