package com.example.home.viewpager2.tab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xiangxiongfly.common.fragment.TextFragment

class TabAdapter(
    fragmentActivity: FragmentActivity,
    private val fragmentList: ArrayList<TextFragment>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}