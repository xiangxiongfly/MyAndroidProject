package com.xiangxiongfly.app.widgets

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class Tab(
    var label: String,
    @DrawableRes var iconSelected: Int,
    @DrawableRes var iconUnselect: Int,
    @ColorRes var colorSelected: Int,
    @ColorRes var colorUnselect: Int,
    var index: Int = 0
)