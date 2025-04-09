package com.xiangxiongfly.myapplication111

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.ArrayRes


class SideIndexBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // 默认配置
    companion object {
        private val DEFAULT_LETTERS = arrayOf(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"
        )
        private const val DEFAULT_TEXT_COLOR = Color.GRAY
        private const val SELECTED_TEXT_COLOR = Color.BLUE
        private const val DEFAULT_TEXT_SIZE_SP = 14f
    }

    // 绘制相关属性
    private var letters: Array<String> = DEFAULT_LETTERS
    private var textColor: Int = DEFAULT_TEXT_COLOR
    private var selectedTextColor: Int = SELECTED_TEXT_COLOR
    private var textSizePx: Float = DEFAULT_TEXT_SIZE_SP.spToPx()

    // 绘制工具
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
    }

    // 触摸交互
    private var selectedPos: Int = -1
    private var textHeight: Float = 0f

    // 提示弹窗
    private var tipView: TextView? = null
    private val windowManager: WindowManager? by lazy {
        context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
    }

    // 回调接口
    var onLetterSelectedListener: OnLetterSelectedListener? = null

    interface OnLetterSelectedListener {
        fun onLetterSelected(letter: String)
        fun onActionUp()
    }

    init {
        setupAttributes(attrs)
        setupPaint()
    }

    private fun Float.spToPx(): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        resources.displayMetrics
    )

    private fun setupAttributes(attrs: AttributeSet?) {
        textColor = DEFAULT_TEXT_COLOR
        selectedTextColor = SELECTED_TEXT_COLOR
        textSizePx = DEFAULT_TEXT_SIZE_SP.spToPx()
        letters = resources.getStringArray(R.array.custom_letters)
    }

    private fun setupPaint() {
        paint.textSize = textSizePx
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minWidth = (paint.measureText("W") + paddingStart + paddingEnd).toInt()
        val resolvedWidth = resolveSize(minWidth, widthMeasureSpec)
        setMeasuredDimension(resolvedWidth, MeasureSpec.getSize(heightMeasureSpec))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val totalHeight = height - paddingTop - paddingBottom
        textHeight = totalHeight.toFloat() / letters.size

        letters.forEachIndexed { index, letter ->
            val x = width / 2f
            val y = paddingTop + textHeight * index + textHeight / 2 +
                    (paint.descent() - paint.ascent()) / 2 - paint.descent()

            paint.color = if (index == selectedPos) selectedTextColor else textColor
            canvas.drawText(letter, x, y, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                event.y.let { y ->
                    calculateSelectedPosition(y).takeIf { it != selectedPos }?.let { pos ->
                        selectedPos = pos
                        invalidate()
                        triggerLetterSelection(pos)
                        showTip(letters[pos])
                    }
                }
                return true
            }

            MotionEvent.ACTION_UP -> {
                resetSelection()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun calculateSelectedPosition(y: Float): Int {
        val rawPos = ((y - paddingTop) / textHeight).toInt()
        return rawPos.coerceIn(0, letters.lastIndex)
    }

    private fun triggerLetterSelection(position: Int) {
        onLetterSelectedListener?.onLetterSelected(letters[position])
    }

    private fun resetSelection() {
        selectedPos = -1
        invalidate()
        onLetterSelectedListener?.onActionUp()
        hideTip()
    }

    private fun showTip(letter: String) {
        tipView = tipView ?: TextView(context).apply {
            textSize = 24f
            setTextColor(Color.WHITE)
            setBackgroundResource(R.drawable.bg_letter_tip)
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

    fun setLettersResource(@ArrayRes resId: Int) {
        letters = resources.getStringArray(resId)
        requestLayout()
        invalidate()
    }

    fun updateLetters(newLetters: Array<String>) {
        letters = newLetters
        requestLayout()
        invalidate()
    }
}
