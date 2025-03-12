package com.example.widgets.customedittext

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.example.widgets.R

class SearchEditText(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseEditText(context, attrs) {

    companion object {
        private const val DEFAULT_SEARCH_TEXT_SIZE = 20
        private const val DEFAULT_SEARCH_TEXT_COLOR = 0x666666
    }

    private val bgDrawable: Drawable?
    private val searchDrawable: Drawable?
    private val searchIconSize: Int
    private val searchText: String?
    private val searchTextSize: Int
    private val searchTextColor: Int

    init {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchEditText)
        bgDrawable = a.getDrawable(R.styleable.SearchEditText_set_background)
        searchDrawable = a.getDrawable(R.styleable.SearchEditText_set_searchIcon)
        searchIconSize =
            a.getDimensionPixelSize(R.styleable.SearchEditText_set_searchIconSize, 0)
        searchText = a.getString(R.styleable.SearchEditText_set_searchText)
        searchTextSize = a.getDimensionPixelSize(
            R.styleable.SearchEditText_set_searchTextSize,
            DEFAULT_SEARCH_TEXT_SIZE
        )
        searchTextColor =
            a.getColor(R.styleable.SearchEditText_set_searchTextColor, DEFAULT_SEARCH_TEXT_COLOR)
        a.recycle()
        setup()
    }

    private fun setup() {
        bgDrawable?.let {
            background = it
        }
        searchDrawable?.let {
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
        if (text.toString().isEmpty()) {
            drawSearch(canvas)
        }
    }

    private fun drawSearch(canvas: Canvas) {
        if (!TextUtils.isEmpty(searchText)) {
            val bounds = Rect()
            val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = searchTextColor
                textSize = searchTextSize.toFloat()
            }
            paint.getTextBounds(searchText, 0, searchText!!.length, bounds)
            canvas.save()
            val fontMetrics = paint.fontMetrics
            // 计算文字的高度
            val textHeight = fontMetrics.bottom - fontMetrics.top
            // 计算文字的垂直居中位置
            val y = height / 2f + (textHeight / 2f - fontMetrics.bottom)
            // 计算文字的水平居中位置
            val x = width / 2f - paint.measureText(searchText) / 2f + searchIconSize / 2
            canvas.drawText(searchText, x, y, paint)
            searchDrawable?.let {
                canvas.translate(
                    (width - searchIconSize) / 2F - bounds.width() / 2,
                    (height - searchIconSize) / 2F
                )
                it.draw(canvas)
            }
            canvas.restore()
        }
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams) {
        if (params.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
        }
        super.setLayoutParams(params)
    }
}