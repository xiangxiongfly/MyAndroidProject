package com.example.custom.navigationbar

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.widgets.navigationbar.TabIndex
import com.example.widgets.navigationbar.TabIndex.Companion.FOUR_INDEX
import com.example.widgets.navigationbar.TabIndex.Companion.ONE_INDEX
import com.example.widgets.navigationbar.TabIndex.Companion.THREE_INDEX
import com.example.widgets.navigationbar.TabIndex.Companion.TWO_INDEX
import com.xiangxiongfly.common.fragment.SimpleFragment

class FragmentSwitchHelper(
    private val fragmentManager: FragmentManager,
    @IdRes private val containerId: Int
) {

    private var currentFragment: Fragment? = null
    private var currentIndex: Int? = null

    fun switchTo(@TabIndex index: Int) {
        if (currentIndex == index) {
            return
        }

        val transaction = fragmentManager.beginTransaction()
        currentFragment?.let {
            transaction.hide(it)
        }

        val fragment = Factory.getOrCreateFragment(index, fragmentManager)
        if (fragment.isAdded) {
            transaction.show(fragment)
        } else {
            transaction.add(
                containerId,
                fragment,
                index.toString()
            )
        }
        transaction.commit()
        currentIndex = index
        currentFragment = fragment
    }

    fun getCurrentIndex() = currentIndex

    fun getCurrentFragment() = currentFragment

    object Factory {
        fun getOrCreateFragment(@TabIndex index: Int, fragmentManager: FragmentManager) =
            when (index) {
                ONE_INDEX -> fragmentManager.findFragmentByTag(index.toString())
                    ?: SimpleFragment.newInstance("ONE")
                TWO_INDEX -> fragmentManager.findFragmentByTag(index.toString())
                    ?: SimpleFragment.newInstance("TWO")
                THREE_INDEX -> fragmentManager.findFragmentByTag(index.toString())
                    ?: SimpleFragment.newInstance("THREE")
                FOUR_INDEX -> fragmentManager.findFragmentByTag(index.toString())
                    ?: SimpleFragment.newInstance("FOUR")
                else -> throw IllegalStateException("非法参数")
            }
    }
}