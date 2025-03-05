package com.xiangxiongfly.common.exts

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.base.BaseActivity
import com.example.base.KEY_TITLE
import com.google.android.flexbox.FlexboxLayout
import com.xiangxiongfly.common.R

fun FlexboxLayout.addElement(
    context: Context,
    title: String,
    actClass: Class<out BaseActivity>
) {
    addView(
        TextView(context).apply {
            background = ContextCompat.getDrawable(context, R.drawable.shape_flex_item)
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
