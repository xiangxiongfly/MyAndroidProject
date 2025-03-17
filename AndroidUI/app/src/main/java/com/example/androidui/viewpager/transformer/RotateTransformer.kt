package com.example.androidui.viewpager.transformer

import android.view.View
import androidx.viewpager.widget.ViewPager

class RotateTransformer : ViewPager.PageTransformer {
    companion object {
        const val MAX_ROTATE = 15F
    }

    override fun transformPage(page: View, position: Float) {
        if (position < -1) { //[-Infinity,-1)
            page.rotation = -MAX_ROTATE
            page.pivotY = page.height.toFloat()
            page.pivotX = page.width.toFloat()
        } else if (position <= 1) { //[-1,1]
            if (position < 0) { //[0，-1]
                page.pivotY = page.height.toFloat()
                page.pivotX = page.width * (0.5F + 0.5F * (-position))
                page.rotation = MAX_ROTATE * position
            } else { //[1,0]
                page.pivotY = page.height.toFloat()
                page.pivotX = page.width * 0.5F * (1 - position)
                page.rotation = MAX_ROTATE * position
            }
        } else { //(1,+Infinity]
            page.rotation = MAX_ROTATE
            page.pivotY = page.height.toFloat()
            page.pivotX = 0F
        }
    }
}