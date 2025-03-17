package com.example.core.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

class WrapContentViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(
                child,
                widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(child.layoutParams.height, MeasureSpec.UNSPECIFIED)
            )
        }
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var height = 0
        when (heightMode) {
            MeasureSpec.EXACTLY -> {
                height = heightSize
            }
            else -> {
                for (i in 0 until childCount) {
                    val child = getChildAt(i)
                    height = Math.max(height, child.measuredHeight)
                }
            }
        }
        super.onMeasure(
            widthMeasureSpec,
            MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        )
    }
}