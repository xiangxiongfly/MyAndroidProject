package com.xiangxiongfly.core.widgets.sideindexbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class SideIndexBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val indexChars = listOf(
        "A", "B", "C", "D", "E", "F", "G", "H", "I",
        "J", "K", "L", "M", "N", "O", "P", "Q", "R",
        "S", "T", "U", "V", "W", "X", "Y", "Z", "#"
    )

    // 绘制相关属性
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = 36f
        color = Color.parseColor("#666666")
    }

    private val highlightPaint = Paint().apply {
        color = Color.parseColor("#20000000")
    }

    // 触摸处理
    private var selectedIndex = -1
    private var cellHeight = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cellHeight = height.toFloat() / indexChars.size
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 绘制高亮背景
        if (selectedIndex != -1) {
            val yPos = selectedIndex * cellHeight
            canvas.drawRect(0f, yPos, width.toFloat(), yPos + cellHeight, highlightPaint)
        }

        // 绘制字母
        indexChars.forEachIndexed { index, char ->
            val xPos = width / 2f
            val yPos = cellHeight * index + cellHeight / 2 + textPaint.textSize / 3
            canvas.drawText(char, xPos, yPos, textPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_MOVE -> {
                val index = (event.y / cellHeight).toInt().coerceIn(0, indexChars.lastIndex)
                if (index != selectedIndex) {
                    selectedIndex = index
                    onIndexChangedListener?.onIndexChanged(indexChars[index])
                    invalidate()
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                selectedIndex = -1
                invalidate()
                onIndexChangedListener?.onIndexReleased()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    fun setOnIndexChangedListener(listener: OnIndexChangedListener) {
        this.onIndexChangedListener = listener
    }

    private var onIndexChangedListener:OnIndexChangedListener?=null

    interface OnIndexChangedListener {
        fun onIndexChanged(index: String)
        fun onIndexReleased()
    }
}
