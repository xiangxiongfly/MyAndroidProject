package com.example.androidui.collapsing

import android.os.Bundle
import com.example.androidui.R
import com.example.core.base.BaseActivity
import com.google.android.material.tabs.TabLayout

class Collapsing2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapsing2)

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.apply {
            addTab(tabLayout.newTab().setText("三国演义"))
            addTab(tabLayout.newTab().setText("水浒传"))
            addTab(tabLayout.newTab().setText("西游记"))
            addTab(tabLayout.newTab().setText("红楼梦"))
        }
    }
}