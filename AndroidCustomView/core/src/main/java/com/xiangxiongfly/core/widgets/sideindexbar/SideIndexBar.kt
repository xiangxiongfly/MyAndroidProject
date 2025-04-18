package com.xiangxiongfly.core.widgets.sideindexbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.core.view.setPadding
import com.xiangxiongfly.core.utils.dp
import com.xiangxiongfly.core.utils.sp

class SideIndexBar @JvmOverloads constructor(
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

    private var onIndexChangedListener: OnIndexChangedListener? = null
    private var letters: List<String> = DEFAULT_LETTERS
    private var selectedIndex: Int = -1
    private var cellHeight: Float = 0F
    private var textColor: Int = Color.GRAY
    private var selectedTextColor: Int = Color.RED
    private var tipView: TextView? = null
    private val windowManager: WindowManager? by lazy {
        context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
    }

    // 绘制相关属性
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = 14F.sp
    }

    private val highlightPaint = Paint().apply {
        color = Color.parseColor("#20000000")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val minWidth = (textPaint.measureText("W") + paddingStart + paddingEnd).toInt()
        val resolvedWidth = resolveSize(minWidth, widthMeasureSpec)
        setMeasuredDimension(resolvedWidth, MeasureSpec.getSize(heightMeasureSpec))
        val totalHeight = measuredHeight - paddingTop - paddingBottom
        cellHeight = if (letters.isNotEmpty()) totalHeight.toFloat() / letters.size else 0F
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (letters.isEmpty()) return
        val fm = textPaint.fontMetrics
        val textHeight = fm.descent - fm.ascent
        val baseYOffset = (cellHeight - textHeight) / 2 - fm.ascent

        letters.forEachIndexed { index, text ->
            val x = width / 2F
            val y = cellHeight * index + baseYOffset + paddingTop
            textPaint.color = if (index == selectedIndex) selectedTextColor else textColor
            canvas.drawText(text, x, y, textPaint)
        }

        // 绘制高亮背景
        if (selectedIndex != -1) {
            val yPos = selectedIndex * cellHeight + paddingTop
            canvas.drawRect(0f, yPos, width.toFloat(), yPos + cellHeight, highlightPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_MOVE -> {
                val index =
                    ((event.y - paddingTop) / cellHeight).toInt().coerceIn(0, letters.lastIndex)
                if (index != selectedIndex) {
                    selectedIndex = index
                    invalidate()
                    onIndexChangedListener?.onIndexChanged(index, letters[index])
                    showTip(letters[index])
                }
                return true
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                selectedIndex = -1
                invalidate()
                onIndexChangedListener?.onIndexReleased()
                hideTip()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    fun setLetters(letters: List<String>?) {
        this.letters = letters ?: DEFAULT_LETTERS
        requestLayout()
    }

    private fun showTip(letter: String) {
        tipView = tipView ?: TextView(context).apply {
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.GRAY)
            setPadding(10.dp)
        }
        tipView?.let { tv ->
            tv.text = letter
            if (tv.parent == null) {
                WindowManager.LayoutParams().apply {
                    gravity = Gravity.CENTER
                    width = WindowManager.LayoutParams.WRAP_CONTENT
                    height = WindowManager.LayoutParams.WRAP_CONTENT
                    flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    windowManager?.addView(tv, this)
                }
            }
            tv.visibility = View.VISIBLE
        }
    }

    private fun hideTip() {
        tipView?.let { tv ->
            windowManager?.removeView(tv)
            tipView = null
        }
    }

    fun setOnIndexChangedListener(listener: OnIndexChangedListener) {
        this.onIndexChangedListener = listener
    }

    interface OnIndexChangedListener {
        fun onIndexChanged(position: Int, letter: String)
        fun onIndexReleased()
    }
}
