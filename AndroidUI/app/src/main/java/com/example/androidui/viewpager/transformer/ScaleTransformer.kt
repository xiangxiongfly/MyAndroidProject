package com.example.androidui.viewpager.transformer

import android.view.View
import androidx.viewpager.widget.ViewPager

/**
 * 从第一页到第二页：
 *  页1的position变化：从0到-1
 *  页2的position变化：从1到0
 */
class ScaleTransformer : ViewPager.PageTransformer {
    companion object {
        const val MIN_SCALE = 0.6F
        const val MIN_ALPHA = 0.5F
    }

    override fun transformPage(page: View, position: Float) {
        if (position < -1) { //[-Infinity,-1)
            page.scaleX = MIN_SCALE
            page.scaleY = MIN_SCALE
            page.alpha = MIN_ALPHA
        } else if (position <= 1) { //[-1,1]
            if (position < 0) { //[0,-1]
                val scale = MIN_SCALE + (1 - MIN_SCALE) * (1 + position)
                page.scaleX = scale
                page.scaleY = scale
                val alpha = MIN_ALPHA + (1 - MIN_ALPHA) * (1 + position)
                page.alpha = alpha
            } else { //[1,0]
                val scale = MIN_SCALE + (1 - MIN_SCALE) * (1 - position)
                page.scaleX = scale
                page.scaleY = scale
                val alpha = MIN_ALPHA + (1 - MIN_ALPHA) * (1 - position)
                page.alpha = alpha
            }
        } else { //(1,+Infinity]
            page.scaleX = MIN_SCALE
            page.scaleY = MIN_SCALE
            page.alpha = MIN_ALPHA
        }
    }
}