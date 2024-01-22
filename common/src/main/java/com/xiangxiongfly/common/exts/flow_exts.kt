package com.xiangxiongfly.common.exts

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.flexbox.FlexboxLayout
import com.xiangxiongfly.common.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE

fun FlexboxLayout.addElement(
    context: Context,
    title: String,
    actClass: Class<out BaseActivity>
) {
    addView(
        TextView(context).apply {
            background = ContextCompat.getDrawable(context, R.drawable.home_shape)
            setTextColor(Color.BLACK)
            text = title
            isAllCaps = false
            textSize = 18F
            setOnClickListener {
                context.startActivity(Intent(context, actClass).apply {
                    putExtra(KEY_TITLE, title)
                })
            }
        }
    )
}
