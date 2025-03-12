package com.example.widgets.navigation

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

class TabView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var ivSelected: ImageView
    private var ivUnselect: ImageView
    private var tvLabel: TextView
    private lateinit var tabItem: Tab

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        val frameLayout = FrameLayout(context).apply {
            layoutParams =
                FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }
        ivSelected = ImageView(context).apply {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }
        ivUnselect = ImageView(context).apply {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }
        frameLayout.addView(ivSelected)
        frameLayout.addView(ivUnselect)
        tvLabel = TextView(context).apply {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            textSize = 12F
        }
        addView(frameLayout)
        addView(tvLabel)
    }

    /**
     * 1表示选中
     * 0表示未选中
     */
    fun setProgress(progress: Float) {
        ivSelected.alpha = progress
        ivUnselect.alpha = 1 - progress
        tvLabel.setTextColor(
            evaluate(
                progress,
                ContextCompat.getColor(context, tabItem.colorUnselect),
                ContextCompat.getColor(context, tabItem.colorSelected)
            )
        )
    }

    /**
     * 设置图片和文本
     */
    fun setData(tab: Tab) {
        this.tabItem = tab
        tvLabel.text = tab.label
        ivSelected.setImageResource(tab.iconSelected)
        ivUnselect.setImageResource(tab.iconUnselect)
        tag = tab.index
    }

    // ArgbEvaluator#evaluate()
    private fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        val startInt = startValue
        val startA = (startInt shr 24 and 0xff) / 255.0f
        var startR = (startInt shr 16 and 0xff) / 255.0f
        var startG = (startInt shr 8 and 0xff) / 255.0f
        var startB = (startInt and 0xff) / 255.0f
        val endInt = endValue.toInt()
        val endA = (endInt shr 24 and 0xff) / 255.0f
        var endR = (endInt shr 16 and 0xff) / 255.0f
        var endG = (endInt shr 8 and 0xff) / 255.0f
        var endB = (endInt and 0xff) / 255.0f

        // convert from sRGB to linear
        startR = Math.pow(startR.toDouble(), 2.2).toFloat()
        startG = Math.pow(startG.toDouble(), 2.2).toFloat()
        startB = Math.pow(startB.toDouble(), 2.2).toFloat()
        endR = Math.pow(endR.toDouble(), 2.2).toFloat()
        endG = Math.pow(endG.toDouble(), 2.2).toFloat()
        endB = Math.pow(endB.toDouble(), 2.2).toFloat()

        // compute the interpolated color in linear space
        var a = startA + fraction * (endA - startA)
        var r = startR + fraction * (endR - startR)
        var g = startG + fraction * (endG - startG)
        var b = startB + fraction * (endB - startB)

        // convert back to sRGB in the [0..255] range
        a *= 255.0f
        r = Math.pow(r.toDouble(), 1.0 / 2.2).toFloat() * 255.0f
        g = Math.pow(g.toDouble(), 1.0 / 2.2).toFloat() * 255.0f
        b = Math.pow(b.toDouble(), 1.0 / 2.2).toFloat() * 255.0f
        return (Math.round(a) shl 24) or (Math.round(r) shl 16) or (Math.round(g) shl 8) or Math.round(
            b
        )
    }
}