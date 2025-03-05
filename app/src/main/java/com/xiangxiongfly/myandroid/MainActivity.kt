package com.xiangxiongfly.myandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.IntRange
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.base.BaseActivity
import com.example.base.BaseFragment
import com.example.custom.CustomFragment
import com.example.home.HomeFragment
import com.example.setting.SettingFragment
import com.example.tools.ToolsFragment
import com.example.widgets.navigation.NavigationBar
import com.example.widgets.navigation.Tab
import com.google.android.material.navigation.NavigationView
import com.gyf.immersionbar.ImmersionBar


class MainActivity : BaseActivity() {

    companion object {
        const val KEY_POSITION: String = "key_position"
        const val POSITION_HOME = 0
        const val POSITION_CUSTOM = 1
        const val POSITION_TOOLS = 2

        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private lateinit var vpMain: ViewPager
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationBar: NavigationBar
    private lateinit var navView: NavigationView
    private lateinit var contentView: LinearLayout
    private lateinit var toolbar: Toolbar

    private var currentPosition = POSITION_CUSTOM

    private val mTitles = arrayOf("首页", "Custom", "工具", "设置")
    private val mFragments = SparseArray<BaseFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        savedInstanceState?.let {
            currentPosition = it.getInt(KEY_POSITION, 0)
        }

        //沉浸式
        ImmersionBar.with(this).titleBar(toolbar).init()

        initBottomNavigation()
        initViewPager()
        initDrawerLayout()
    }

    private fun initViews() {
        vpMain = findViewById(R.id.vp_main)
        navigationBar = findViewById(R.id.bottom_navigation)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        contentView = findViewById(R.id.content_view)
        toolbar = findViewById(R.id.toolbar)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_POSITION, currentPosition)
        super.onSaveInstanceState(outState)
    }

    private fun initBottomNavigation() {
        navigationBar.init(
            listOf(
                Tab(
                    mTitles[0],
                    R.drawable.tab_home_selected,
                    R.drawable.tab_home_unselect,
                    R.color.tab_selected_color,
                    R.color.tab_unselect_color,
                    0,
                ),
                Tab(
                    mTitles[1],
                    R.drawable.tab_friends_selected,
                    R.drawable.tab_friends_unselect,
                    R.color.tab_selected_color,
                    R.color.tab_unselect_color,
                    1
                ),
                Tab(
                    mTitles[2],
                    R.drawable.tab_find_selected,
                    R.drawable.tab_find_unselect,
                    R.color.tab_selected_color,
                    R.color.tab_unselect_color,
                    2
                ),
                Tab(
                    mTitles[3],
                    R.drawable.tab_setting_selected,
                    R.drawable.tab_setting_unselect,
                    R.color.tab_selected_color,
                    R.color.tab_unselect_color,
                    3
                ),
            ),
            currentPosition
        )
        navigationBar.setOnItemSelectedListener(
            object : NavigationBar.OnItemSelectedListener {
                override fun onItemSelected(index: Int) {
                    currentPosition = index
                    vpMain.setCurrentItem(currentPosition, false)
                    when (index) {
                        0 -> Log.i("TAG", "首页")
                        1 -> Log.i("TAG", "朋友圈")
                        2 -> Log.i("TAG", "发现")
                        3 -> Log.i("TAG", "设置")
                    }
                }
            }
        )
        navigationBar.setOnItemReselectedListener(
            object : NavigationBar.OnItemReselectedListener {
                override fun onItemReselected(index: Int) {
                    when (index) {
                        0 -> Log.i("TAG", "首页2")
                        1 -> Log.i("TAG", "朋友圈2")
                        2 -> Log.i("TAG", "发现2")
                        3 -> Log.i("TAG", "设置2")
                    }
                }
            }
        )
    }

    private fun initViewPager() {
        vpMain.offscreenPageLimit = mTitles.size
        vpMain.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return mTitles.size
            }

            override fun getItem(position: Int): Fragment {
                return createFragment(position)
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val tabFragment = super.instantiateItem(container, position) as BaseFragment
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
                navigationBar.setPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                currentPosition = position
                navigationBar.setTab(currentPosition)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        vpMain.setCurrentItem(currentPosition, false)
    }

    private fun createFragment(@IntRange(from = 0, to = 3) position: Int): BaseFragment {
        return when (position) {
            0 -> HomeFragment.newInstance(mTitles[position])
            1 -> CustomFragment.newInstance(mTitles[position])
            2 -> ToolsFragment.newInstance(mTitles[position])
            3 -> SettingFragment.newInstance(mTitles[position])
            else -> throw IllegalArgumentException("错误Fragment")
        }
    }

    private fun initDrawerLayout() {
        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                Log.i("TAG", "DrawerLayout滑动中")
            }

            override fun onDrawerOpened(drawerView: View) {
                Log.i("TAG", "DrawerLayout打开")
            }

            override fun onDrawerClosed(drawerView: View) {
                Log.i("TAG", "DrawerLayout关闭")
            }

            override fun onDrawerStateChanged(newState: Int) {
                Log.i("TAG", "DrawerLayout状态变化")
            }
        })
        navView.setNavigationItemSelectedListener { menuItem ->
            Toast.makeText(this, menuItem.title, Toast.LENGTH_SHORT).show()
            drawerLayout.closeDrawers()
            false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.e("TAG", "onSupportNavigateUp")
        return super.onSupportNavigateUp()
    }
}

