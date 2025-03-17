package com.example.androidui.viewpager.no_scroll_vp

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * 禁止水平滚动的ViewPager
 */
class NoScrollViewPager(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun executeKeyEvent(event: KeyEvent): Boolean {
        return false
    }
}