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
import com.example.home.HomeFragment
import com.example.jetpack.JetpackFragment
import com.example.setting.SettingFragment
import com.example.tools.ToolsFragment
import com.google.android.material.navigation.NavigationView
import com.gyf.immersionbar.ImmersionBar
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.BaseFragment
import com.xiangxiongfly.common.widgets.navigation.BottomNavigation
import com.xiangxiongfly.common.widgets.navigation.TabItem


class MainActivity : BaseActivity() {

    companion object {
        const val KEY_POSITION: String = "key_position"
        const val POSITION_HOME = 0
        const val POSITION_JETPACK = 1
        const val POSITION_TOOLS = 2

        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private lateinit var vpMain: ViewPager
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigation: BottomNavigation
    private lateinit var navView: NavigationView
    private lateinit var contentView: LinearLayout
    private lateinit var toolbar: Toolbar

    private var currentPosition = POSITION_HOME

    private val mTitles = arrayOf("首页", "Jetpack", "工具", "设置")
    private val mFragments = SparseArray<BaseFragment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        savedInstanceState?.let {
            currentPosition = it.getInt(KEY_POSITION, 0)
        }

        //沉浸式
        ImmersionBar.with(this).titleBar(toolbar).init()

        initBottomNavigation()
        initViewPager()
        initDrawerLayout()
    }

    private fun initView() {
        vpMain = findViewById(R.id.vp_main)
        bottomNavigation = findViewById(R.id.bottom_navigation)
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
        bottomNavigation.init(
            listOf<TabItem>(
                TabItem(
                    R.drawable.tab_home_selected,
                    R.drawable.tab_home_unselect,
                    mTitles[0]
                ),
                TabItem(
                    R.drawable.tab_friends_selected,
                    R.drawable.tab_friends_unselect,
                    mTitles[1]
                ),
                TabItem(
                    R.drawable.tab_find_selected,
                    R.drawable.tab_find_unselect,
                    mTitles[2]
                ),
                TabItem(
                    R.drawable.tab_setting_selected,
                    R.drawable.tab_setting_unselect,
                    mTitles[3]
                )
            )
        )
        bottomNavigation.setCurrentTab(currentPosition)
        bottomNavigation.setOnItemSelectedListener(
            object : BottomNavigation.OnItemSelectedListener {
                override fun onItemSelected(tabItem: TabItem) {
                    currentPosition = tabItem.index
                    vpMain.setCurrentItem(currentPosition, false)
                    when (tabItem.index) {
                        0 -> Log.i("TAG", "首页")
                        1 -> Log.i("TAG", "朋友圈")
                        2 -> Log.i("TAG", "发现")
                        3 -> Log.i("TAG", "设置")
                    }
                }
            }
        )
        bottomNavigation.setOnItemReselectedListener(
            object : BottomNavigation.OnItemReselectedListener {
                override fun onItemReselected(tabItem: TabItem) {
                    when (tabItem.index) {
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
                bottomNavigation.setPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                currentPosition = position
                bottomNavigation.setCurrentTab(currentPosition)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        vpMain.setCurrentItem(currentPosition, false)
    }

    private fun createFragment(@IntRange(from = 0, to = 3) position: Int): BaseFragment {
        return when (position) {
            0 -> HomeFragment.newInstance(mTitles[position])
            1 -> JetpackFragment.newInstance(mTitles[position])
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

