package com.example.widgets.navigation

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class NavigationBar @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null
) : LinearLayout(mContext, attrs), View.OnClickListener {

    private var selectedIndex: Int = 0
    private var tabCount = 0;
    private var onItemSelectedListener: OnItemSelectedListener? = null
    private var onItemReselectedListener: OnItemReselectedListener? = null

    init {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER
    }

    fun init(tabs: List<Tab>, defaultIndex: Int = 0) {
        selectedIndex = defaultIndex
        tabCount = tabs.size
        val layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
        layoutParams.gravity = Gravity.CENTER
        for (tab in tabs) {
            val tabView = TabView(mContext).apply {
                setData(tab)
                if (tab.index == defaultIndex) setProgress(1F) else setProgress(0F)
                setOnClickListener(this@NavigationBar)
            }
            addView(tabView, layoutParams)
        }
    }

    fun setOnItemSelectedListener(listener: OnItemSelectedListener) {
        this.onItemSelectedListener = listener
    }

    fun setOnItemReselectedListener(listener: OnItemReselectedListener) {
        this.onItemReselectedListener = listener
    }

    fun setTab(index: Int) {
        val tabView = getChildAt(index)
        clickTab(tabView as TabView)
    }

    override fun onClick(view: View) {
        if (view is TabView) {
            clickTab(view)
        }
    }

    private var lock = false
    private fun clickTab(tabView: TabView) {
        if (lock) {
            return
        }
        lock = true
        val index = tabView.tag as Int
        Log.e("TAG", "index:${index}  selectedIndex:${selectedIndex}")
        if (selectedIndex != index) {
            onItemSelectedListener?.onItemSelected(index)
            tabView.setProgress(1F)
            (getChildAt(selectedIndex) as TabView).setProgress(0F)
            selectedIndex = index
        } else {
            onItemReselectedListener?.onItemReselected(index)
        }
        lock = false
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
        fun onItemSelected(index: Int)
    }

    interface OnItemReselectedListener {
        fun onItemReselected(index: Int)
    }
}