package com.xiangxiongfly.common.widgets.navigation

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class BottomNavigation @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null
) : LinearLayout(mContext, attrs), View.OnClickListener {

    private var selectedView: TabView? = null
    private var tabCount = -1
    var mOnItemSelectedListener: OnItemSelectedListener? = null
    var mOnItemReselectedListener: OnItemReselectedListener? = null

    init {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER
    }

    fun init(tabItems: List<TabItem>) {
        val layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
        layoutParams.gravity = Gravity.CENTER
        tabCount = tabItems.size
        tabItems.forEachIndexed { index, tabItem ->
            tabItem.index = index
            val tabView = TabView(mContext)
            tabView.tag = tabItems[index]
            tabView.setIconAndLabel(tabItem.iconSelected, tabItem.iconUnselect, tabItem.label)
            tabView.setOnClickListener(this)
            addView(tabView, layoutParams)
        }
    }

    fun setOnItemSelectedListener(listener: OnItemSelectedListener) {
        this.mOnItemSelectedListener = listener
    }

    fun setOnItemReselectedListener(listener: OnItemReselectedListener) {
        this.mOnItemReselectedListener = listener
    }

    fun setCurrentTab(index: Int) {
        if (index in 0 until tabCount) {
            val tabView = getChildAt(index)
            onClick(tabView)
        }
    }

    override fun onClick(v: View) {
        if (v is TabView) {
            if (selectedView != v) {
                mOnItemSelectedListener?.onItemSelected(v.tag as TabItem)
                selectedView?.setProgress(0F)
                v.setProgress(1F)
                selectedView = v
            } else {
                mOnItemReselectedListener?.onItemReselected(v.tag as TabItem)
            }
        }
    }

    fun setPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (positionOffset > 0) {
            val left = getChildAt(position) as TabView
            val right: TabView = getChildAt(position + 1) as TabView
            left.setProgress(1 - positionOffset)
            right.setProgress(positionOffset)
        }
    }

    interface OnItemSelectedListener {
        fun onItemSelected(tabItem: TabItem)
    }

    interface OnItemReselectedListener {
        fun onItemReselected(tabItem: TabItem)
    }
}