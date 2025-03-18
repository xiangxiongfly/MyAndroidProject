package com.xiangxiongfly.customview.navigationbar

import android.os.Bundle
import android.util.Log
import com.xiangxiongfly.core.base.BaseActivity
 import com.xiangxiongfly.core.widgets.navigationbar.NavigationBar
import com.xiangxiongfly.core.widgets.navigationbar.Tab
import com.xiangxiongfly.core.widgets.navigationbar.TabIndex.Companion.FOUR_INDEX
import com.xiangxiongfly.core.widgets.navigationbar.TabIndex.Companion.ONE_INDEX
import com.xiangxiongfly.core.widgets.navigationbar.TabIndex.Companion.THREE_INDEX
import com.xiangxiongfly.core.widgets.navigationbar.TabIndex.Companion.TWO_INDEX
import com.xiangxiongfly.customview.R

class NavigationBarActivity : BaseActivity() {
    private lateinit var navigationBar: NavigationBar

    private val fragmentHelper by lazy {
        FragmentSwitchHelper(
            supportFragmentManager,
            R.id.fragment_container
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_bar)
        navigationBar = findViewById(R.id.navigation_bar)

        val tabs = listOf<Tab>(
            Tab(
                ONE_INDEX,
                "ONE",
                R.drawable.tab_home_unselect,
                R.drawable.tab_home_selected
            ),
            Tab(
                TWO_INDEX,
                "TWO",
                R.drawable.tab_friends_unselect,
                R.drawable.tab_friends_selected
            ),
            Tab(
                THREE_INDEX,
                "THREE",
                R.drawable.tab_find_unselect,
                R.drawable.tab_find_selected
            ),
            Tab(
                FOUR_INDEX,
                "FOUR",
                R.drawable.tab_setting_unselect,
                R.drawable.tab_setting_selected
            )
        )
        navigationBar.setData(tabs)
        navigationBar.setOnItemSelectedListener {
            Log.e("TAG", "点击：$it")
            fragmentHelper.switchTo(it)
        }
        navigationBar.setOnItemReselectListener {
            Log.e("TAG", "重复点击：$it")
        }
        fragmentHelper.switchTo(ONE_INDEX)
    }
}