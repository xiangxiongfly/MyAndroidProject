package com.xiangxiongfly.androidtools.clickarea.view

import android.content.Context
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.exts.dp
import com.xiangxiongfly.common.utils.ToastUtils

class ParentTouchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var text1: TextView

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount > 0) {
            text1 = findViewById(R.id.text1)
            text1.setOnClickListener {
                ToastUtils.show(text1.text.toString())
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { e ->
            if (e.action == MotionEvent.ACTION_DOWN) {
                if (isExpandView(text1, e.rawX, e.rawY, 100.dp)) {
                    text1.performClick()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 是否在View的扩大区域
     *
     * @param targetView 目标View
     * @param touchX 点击的位置
     * @param touchY 点击的位置
     * @param expandSize 扩大区域的大小
     * @return
     */
    private fun isExpandView(
        targetView: View,
        touchX: Float,
        touchY: Float,
        expandSize: Int = 15.dp
    ): Boolean {
        // 获取目标View的Rect
        val rect = RectF()
        val location = IntArray(2)
        // 获取目标View的坐标
        targetView.getLocationOnScreen(location)
        val childX = location[0].toFloat()
        val childY = location[1].toFloat()
        rect.set(
            childX,
            childY,
            childX + targetView.width,
            childY + targetView.height
        )

        // 设置扩大区域后的Rect
        rect.apply {
            left -= expandSize
            top -= expandSize
            right += expandSize
            bottom += expandSize
        }

        // 判断是否在扩大区域内
        return rect.contains(touchX, touchY)
    }
}