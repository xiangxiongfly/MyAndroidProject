package com.example.widgets.navigationbar

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.util.StateSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.widgets.R

class TabView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val paddingVertical = context.resources.getDimension(R.dimen.tab_padding_vertical).toInt()
    private val iconSize = context.resources.getDimension(R.dimen.tab_icon_size).toInt()
    private val fontSize = context.resources.getDimension(R.dimen.tab_text_size)

    init {
        layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT).apply {
            weight = 1F
            orientation = VERTICAL
        }
        gravity = Gravity.CENTER
        setPadding(0, paddingVertical, 0, paddingVertical)
    }

    fun setData(tab: Tab) {
        removeAllViews()
        addViews(tab)
    }

    private fun addViews(tab: Tab) {
        addView(createIcon(tab.tabIconDefault, tab.tabIconSelected))
        addView(createText(tab.label, tab.tabTextColorDefault, tab.tabTextColorSelected))
    }

    private fun createIcon(
        @DrawableRes tabIconDefault: Int,
        @DrawableRes tabIconSelected: Int
    ): ImageView {
        val drawable = StateListDrawable().apply {
            addState(
                intArrayOf(android.R.attr.state_selected),
                ContextCompat.getDrawable(context, tabIconSelected)
            )
            addState(StateSet.NOTHING, ContextCompat.getDrawable(context, tabIconDefault))
        }
        return ImageView(context).apply {
            layoutParams = LayoutParams(iconSize, iconSize)
            setImageDrawable(drawable)
            isSelected = false
        }
    }

    private fun createText(
        text: String,
        @ColorRes textColorDefault: Int,
        @ColorRes textColorSelected: Int
    ): TextView {
        val states = arrayOf(
            intArrayOf(android.R.attr.state_selected),
            intArrayOf()
        )
        val colors = intArrayOf(
            ContextCompat.getColor(context, textColorSelected),
            ContextCompat.getColor(context, textColorDefault)
        )
        val colorStateList = ColorStateList(states, colors)
        return TextView(context).apply {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            setText(text)
            setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize)
            setTextColor(colorStateList)
            isSelected = false
        }
    }

    fun selected(isSelected: Boolean) {
        children.forEach {
            it.isSelected = isSelected
        }
    }
}