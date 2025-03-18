package com.xiangxiongfly.core.widgets.customedittext

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup

open class BaseEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.editTextStyle
) : androidx.appcompat.widget.AppCompatEditText(context, attrs, defStyleAttr) {

    companion object {
        @JvmStatic
        protected val DEFAULT_DRAWABLE = ColorDrawable(0xFFFFFFFF.toInt())
    }

    init {
        gravity = Gravity.CENTER_VERTICAL
        background = DEFAULT_DRAWABLE
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams) {
        if (params.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
        }
        super.setLayoutParams(params)
    }
}