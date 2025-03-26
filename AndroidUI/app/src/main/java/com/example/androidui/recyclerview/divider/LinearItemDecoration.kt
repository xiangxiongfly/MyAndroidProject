package com.example.androidui.recyclerview.divider

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView线性布局的分割线
 */
class LinearItemDecoration(
    private val dividerSize: Float = 2F,
    private val dividerColor: Int = Color.GRAY,
    private val startMargin: Int = 0,
    private val endMargin: Int = 0
) : RecyclerView.ItemDecoration() {

    private val paint by lazy {
        Paint().apply {
            color = dividerColor
            strokeWidth = dividerSize
        }
    }

    /**
     * 设置分割线大小
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutManager = parent.layoutManager as LinearLayoutManager
        if (layoutManager.orientation == LinearLayoutManager.VERTICAL) {
            outRect.bottom = paint.strokeWidth.toInt()
        } else {
            outRect.right = paint.strokeWidth.toInt()
        }
    }

    /**
     * 绘制分割线
     */
    override fun onDraw(
        c: Canvas, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutManager = parent.layoutManager as LinearLayoutManager
        if (layoutManager.orientation == LinearLayoutManager.VERTICAL) {
            drawVerticalDivider(c, parent)
        } else {
            drawHorizontalDivider(c, parent)
        }
    }

    /**
     * 垂直方向分割线绘制
     */
    private fun drawVerticalDivider(canvas: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft + startMargin
        val right = parent.measuredWidth - parent.paddingRight - endMargin
        val childCount = parent.childCount
        for (i in 0 until childCount - 1) { //最后一条不显示
            val childView = parent.getChildAt(i)
            val layoutParams = childView.layoutParams as RecyclerView.LayoutParams
            val top = childView.bottom + layoutParams.bottomMargin
            val bottom = top + dividerSize
            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }

    /**
     * 水平方向分割线绘制
     */
    private fun drawHorizontalDivider(canvas: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop + startMargin
        val bottom = parent.measuredHeight - parent.paddingBottom - endMargin
        val childCount = parent.childCount
        for (i in 0 until childCount - 1) { //最后一条不显示
            val childView = parent.getChildAt(i)
            val layoutParams = childView.layoutParams as RecyclerView.LayoutParams
            val left = childView.right + layoutParams.rightMargin
            val right = left + dividerSize
            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }
}