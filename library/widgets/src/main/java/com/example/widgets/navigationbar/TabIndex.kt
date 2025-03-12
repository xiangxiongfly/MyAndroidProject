package com.example.widgets.navigationbar

import androidx.annotation.IntDef
import com.example.widgets.navigationbar.TabIndex.Companion.FOUR_INDEX
import com.example.widgets.navigationbar.TabIndex.Companion.ONE_INDEX
import com.example.widgets.navigationbar.TabIndex.Companion.THREE_INDEX
import com.example.widgets.navigationbar.TabIndex.Companion.TWO_INDEX

@Retention(AnnotationRetention.SOURCE)
@IntDef(ONE_INDEX, TWO_INDEX, THREE_INDEX, FOUR_INDEX)
annotation class TabIndex {
    companion object {
        const val ONE_INDEX = 0
        const val TWO_INDEX = 1
        const val THREE_INDEX = 2
        const val FOUR_INDEX = 3
    }
}