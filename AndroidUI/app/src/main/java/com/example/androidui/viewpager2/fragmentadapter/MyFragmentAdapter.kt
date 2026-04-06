package com.example.androidui.viewpager2.fragmentadapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyFragmentAdapter(activity: FragmentActivity, private val fragments: List<Fragment>) :
    FragmentStateAdapter(activity) {
    override fun createFragment(position: Int) = fragments[position]

    override fun getItemCount() = fragments.size
}