package com.xiangxiongfly.common.widgets.navigation

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.xiangxiongfly.common.R

class TabView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {
    private val COLOR_SELECTED: Int = resources.getColor(R.color.color_selected)
    private val COLOR_UNSELECT: Int = resources.getColor(R.color.color_unselect)

    private var ivSelected: ImageView
    private var ivUnselect: ImageView
    private var tvLabel: TextView

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        inflate(context, R.layout.tab_view, this)
        ivSelected = findViewById(R.id.iv_selected)
        ivUnselect = findViewById(R.id.iv_unselect)
        tvLabel = findViewById(R.id.tv_label)
        setProgress(0F)
    }

    /**
     * 1表示选中
     * 0表示未选中
     */
    fun setProgress(progress: Float) {
        ivUnselect.alpha = 1 - progress
        ivSelected.alpha = progress
        tvLabel.setTextColor(evaluate(progress, COLOR_UNSELECT, COLOR_SELECTED))
    }

    /**
     * 设置图片和文本
     */
    fun setIconAndLabel(
        @DrawableRes iconSelected: Int,
        @DrawableRes iconUnselect: Int,
        label: String
    ) {
        ivSelected.setImageResource(iconSelected)
        ivUnselect.setImageResource(iconUnselect)
        tvLabel.text = label
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
        return (Math.round(a) shl 24) or
                (Math.round(r) shl 16) or
                (Math.round(g) shl 8) or
                Math.round(b)
    }
}