package com.example.widgets.navigation

import androidx.annotation.DrawableRes

data class TabItem(
    @DrawableRes var iconSelected: Int,
    @DrawableRes var iconUnselect: Int,
    var label: String
) {
    var index: Int = -1
}
