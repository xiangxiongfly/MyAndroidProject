package com.example.widgets.setting_item

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.base.utils.dp
import com.example.base.utils.dp2px

object SettingItemFactory {
    /**
     * 创建容器
     */
    fun newContainerView(context: Context): LinearLayout {
        return LinearLayout(context).apply {
            layoutParams =
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER_VERTICAL
                )
        }
    }

    /**
     * 创建左边视图
     */
    fun newLeftView(context: Context): TextView {
        return TextView(context).apply {
            val leftLayoutParams =
                LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1F)
            leftLayoutParams.gravity = Gravity.CENTER_VERTICAL
            layoutParams = leftLayoutParams
            isSingleLine = true
            ellipsize = TextUtils.TruncateAt.END
            setPaddingRelative(15.dp, 12.dp, 15.dp, 12.dp)
            gravity = Gravity.START or Gravity.CENTER_VERTICAL
        }
    }

    /**
     * 创建右边视图
     */
    fun newRightView(context: Context): TextView {
        return TextView(context).apply {
            val rightLayoutParams =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            rightLayoutParams.gravity = Gravity.CENTER_VERTICAL
            layoutParams = rightLayoutParams
            isSingleLine = true
            ellipsize = TextUtils.TruncateAt.END
            setPaddingRelative(15.dp, 12.dp, 15.dp, 12.dp)
            gravity = Gravity.END or Gravity.CENTER_VERTICAL
        }
    }

    /**
     * 创建下划线
     */
    fun newLine(context: Context): View {
        return View(context).apply {
            layoutParams =
                FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM)
        }
    }
}