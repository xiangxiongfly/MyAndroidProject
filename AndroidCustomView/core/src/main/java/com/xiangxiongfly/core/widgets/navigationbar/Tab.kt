package com.xiangxiongfly.core.widgets.navigationbar

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.xiangxiongfly.core.R

data class Tab(
    @TabIndex val index: Int,
    val label: String,
    @DrawableRes val tabIconDefault: Int,
    @DrawableRes val tabIconSelected: Int,
    @ColorRes val tabTextColorDefault: Int = R.color.tab_unselect_color,
    @ColorRes val tabTextColorSelected: Int = R.color.tab_selected_color
)