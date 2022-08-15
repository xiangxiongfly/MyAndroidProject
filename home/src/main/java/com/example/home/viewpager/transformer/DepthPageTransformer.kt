package com.example.home.viewpager.transformer

import android.view.View
import androidx.viewpager.widget.ViewPager

class DepthPageTransformer : ViewPager.PageTransformer {
    companion object {
        const val MIN_SCALE = 0.75F
    }

    override fun transformPage(page: View, position: Float) {
        if (position < -1) {
            page.alpha = 0F
        } else if (position <= 1) {
            if (position <= 0) {
                page.alpha = 1F
                page.translationX = 0F
                page.scaleX = 1F
                page.scaleY = 1F
            } else {
                page.alpha = 1 - position
                page.translationX = page.width * (-position)
                val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
                page.scaleX = scaleFactor
                page.scaleY = scaleFactor
            }
        } else {
            page.alpha = 0F
        }
    }
}