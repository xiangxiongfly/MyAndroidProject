package com.xiangxiongfly.app.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class NavigationBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var selectedIndex: Int = 0
    private var tabCount = 0;
    private var onItemSelectedClick: ((Int) -> Unit)? = null
    private var onItemReselectedClick: ((Int) -> Unit)? = null

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
    }

    fun init(tabs: List<Tab>, defaultIndex: Int = 0) {
        tabCount = tabs.size
        selectedIndex = defaultIndex
        for (tab in tabs) {
            addView(createTabView(tab))
        }
    }

    private fun createTabView(tab: Tab): TabView {
        return TabView(context).apply {
            setData(tab)
            layoutParams = LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F).apply {
                gravity = Gravity.CENTER
            }
            if (tab.index == selectedIndex) {
                setAlphaProgress(1F)
                isSelected = true
            } else {
                setAlphaProgress(0F)
                isSelected = false
            }
            setOnClickListener {
                clickTab(it)
            }
        }
    }

    fun setOnItemSelectedClickListener(listener: (Int) -> Unit) {
        this.onItemSelectedClick = listener
    }

    fun setOnItemReselectedClickListener(listener: (Int) -> Unit) {
        this.onItemReselectedClick = listener
    }

    fun scrollTab(index: Int) {
        selectTab(selectedIndex, index)
    }

    private fun clickTab(tabView: View) {
        val lastIndex = selectedIndex
        val selectedIndex = tabView.tag as Int
        if (selectedIndex != lastIndex) {
            selectTab(lastIndex, selectedIndex)
        } else {
            onItemReselectedClick?.invoke(selectedIndex)
        }
    }

    private fun selectTab(lastIndex: Int, index: Int) {
        selectedIndex = index
        if (!getChildAt(selectedIndex).isSelected) {
            (getChildAt(selectedIndex) as TabView).apply {
                isSelected = true
                setAlphaProgress(1F)
            }
            (getChildAt(lastIndex) as TabView).apply {
                isSelected = false
                setAlphaProgress(0F)
            }
            onItemSelectedClick?.invoke(selectedIndex)
        }
    }

    fun setPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (positionOffset > 0) {
            val left = getChildAt(position) as TabView
            val right: TabView = getChildAt(position + 1) as TabView
            left.setAlphaProgress(1 - positionOffset)
            right.setAlphaProgress(positionOffset)
        }
    }
}