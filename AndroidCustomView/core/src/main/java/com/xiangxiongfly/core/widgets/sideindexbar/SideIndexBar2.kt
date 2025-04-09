package com.xiangxiongfly.core.widgets.sideindexbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.xiangxiongfly.core.utils.dp
import com.xiangxiongfly.core.utils.sp

class SideIndexBar2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private val DEFAULT_LETTERS = listOf(
            "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z", "#"
        )
    }

    private var letters: List<String> = DEFAULT_LETTERS
    private var selectedIndex: Int = -1
    private var cellHeight: Float = 0F
    private var textColor: Int = Color.GRAY
    private var selectedTextColor: Int = Color.RED
    private var onIndexChangedListener: OnIndexChangedListener? = null

    // 绘制相关属性
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = 14F.sp
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 获取文字高度
        val textHeight = textPaint.descent() - textPaint.ascent()
        // 文字间距
        val verticalSpacing = 4.dp
        // 内容总高度
        val contentHeight = letters.size * (textHeight + verticalSpacing)
        // 所需高度
        val desiredHeight = (contentHeight + paddingTop + paddingBottom).toInt()
        // 最小宽度
        val minWidth = (textPaint.measureText("W") + paddingStart + paddingEnd).toInt()
        // 计算宽度
        val resolvedWidth = resolveSize(minWidth, widthMeasureSpec)
        val resolvedHeight = resolveSize(desiredHeight, heightMeasureSpec)
        setMeasuredDimension(resolvedWidth, resolvedHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        letters.forEachIndexed { index, letter ->
            // 计算垂直居中基线
            val textHeight = textPaint.descent() - textPaint.ascent()
            val verticalSpacing = 4.dp
            val lineHeight = textHeight + verticalSpacing
            val x = width / 2f
            val y = paddingTop + lineHeight * index + (lineHeight - verticalSpacing) / 2 +
                    (textPaint.descent() - textPaint.ascent()) / 2 - textPaint.descent()
            textPaint.color = if (index == selectedIndex) selectedTextColor else textColor
            canvas.drawText(letter, x, y, textPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_MOVE -> {
                // 计算实际行高
                val textHeight = textPaint.descent() - textPaint.ascent()
                val verticalSpacing = 4.dp
                val lineHeight = textHeight + verticalSpacing

                // 计算选中位置
                val y = event.y - paddingTop
                val rawPos = (y / lineHeight).toInt()
                val pos = rawPos.coerceIn(0, letters.lastIndex)

                if (pos != selectedIndex) {
                    selectedIndex = pos
                    invalidate()
                    onIndexChangedListener?.onIndexChanged(selectedIndex, letters[selectedIndex])
                }
                return true


                val index =
                    ((event.y - paddingTop) / cellHeight).toInt().coerceIn(0, letters.lastIndex)
                if (index != selectedIndex) {
                    selectedIndex = index
                    invalidate()
                    onIndexChangedListener?.onIndexChanged(index, letters[index])
                }
                return true
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                selectedIndex = -1
                invalidate()
                onIndexChangedListener?.onIndexReleased()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    fun setLetters(letters: List<String>?) {
        this.letters = letters ?: DEFAULT_LETTERS
        requestLayout()
    }

    fun setOnIndexChangedListener(listener: OnIndexChangedListener) {
        this.onIndexChangedListener = listener
    }

    interface OnIndexChangedListener {
        fun onIndexChanged(position: Int, letter: String)
        fun onIndexReleased()
    }
}
