package com.xiangxiongfly.core.widgets.customedittext

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.xiangxiongfly.core.R
import com.xiangxiongfly.core.utils.sp

class SearchEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseEditText(context, attrs) {

    private val labelPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = searchLabelColor
            textSize = searchLabelSize.toFloat()
        }
    }

    private val centerX: Float by lazy { width / 2f }
    private val centerY: Float by lazy { height / 2f }

    private val bgDrawable: Drawable?
    private val searchIcon: Drawable?
    private val searchIconSize: Int
    private val searchLabel: String?
    private val searchLabelSize: Int
    private val searchLabelColor: Int

    init {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchEditText)
        a.let {
            bgDrawable = it.getDrawable(R.styleable.SearchEditText_set_background)
            searchIcon = it.getDrawable(R.styleable.SearchEditText_set_searchIcon)
            searchIconSize =
                it.getDimensionPixelSize(R.styleable.SearchEditText_set_searchIconSize, 0)
            searchLabel = it.getString(R.styleable.SearchEditText_set_searchLabel)
            searchLabelSize =
                it.getDimensionPixelSize(R.styleable.SearchEditText_set_searchLabelSize, 10.sp)
            searchLabelColor =
                it.getColor(R.styleable.SearchEditText_set_searchLabelColor, 0x666666)
        }
        a.recycle()

        setup()
    }

    private fun setup() {
        bgDrawable?.let {
            background = it
        }
        searchIcon?.let {
            if (searchIconSize > 0) {
                it.setBounds(0, 0, searchIconSize, searchIconSize)
            } else {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            }
        }
        imeOptions = EditorInfo.IME_ACTION_SEARCH
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        doDraw(canvas)
    }

    private fun doDraw(canvas: Canvas) {
        text ?: return
        if (text.toString().isEmpty()) {
            if (searchLabel == null && searchIcon != null) {
                drawSingleIcon(canvas)
            } else if (searchLabel != null && searchIcon == null) {
                drawSingleLabel(canvas)
            } else {
                drawSearch(canvas)
            }
        }
    }

    private fun drawSearch(canvas: Canvas) {
        val textWidth = labelPaint.measureText(searchLabel)
        val fontMetrics = labelPaint.fontMetrics
        val textHeight = fontMetrics.bottom - fontMetrics.top
        val allWidth = textWidth + searchIconSize
        val xOfLabel = centerX - allWidth / 2 + searchIconSize
        val yOfLabel = centerY + (textHeight / 2f - fontMetrics.bottom)
        canvas.drawText(searchLabel!!, xOfLabel, yOfLabel, paint)
        canvas.save()
        val xOfIcon = centerX - allWidth / 2
        val yOfIcon = centerY - searchIconSize / 2
        canvas.translate(xOfIcon, yOfIcon)
        searchIcon!!.draw(canvas)
        canvas.restore()
    }

    private fun drawSingleLabel(canvas: Canvas) {
        // 获取文本宽度
        val textWidth = labelPaint.measureText(searchLabel)
        // 获取文本高度
        val fontMetrics = labelPaint.fontMetrics
        val textHeight = fontMetrics.bottom - fontMetrics.top
        // 计算文本坐标
        val x = centerX - textWidth / 2
        val y = centerY + (textHeight / 2f - fontMetrics.bottom)
        canvas.drawText(searchLabel!!, x, y, paint)
    }

    private fun drawSingleIcon(canvas: Canvas) {
        canvas.save()
        val x = centerX - searchIconSize / 2
        val y = centerY - searchIconSize / 2
        canvas.translate(x, y)
        searchIcon!!.draw(canvas)
        canvas.restore()
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams) {
        if (params.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
        }
        super.setLayoutParams(params)
    }
}