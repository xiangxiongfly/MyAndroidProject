package com.example.widgets.customedittext

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
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
    private val searchDrawableSize: Int
    private val searchText: String?
    private val searchTextSize: Int
    private val searchTextColor: Int
    private var isNeedDraw = true // 是否需要绘制

    init {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchEditText)
        bgDrawable = a.getDrawable(R.styleable.SearchEditText_set_background)
        searchDrawable = a.getDrawable(R.styleable.SearchEditText_set_searchIcon)
        searchDrawableSize =
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
            if (searchDrawableSize > 0) {
                it.setBounds(0, 0, searchDrawableSize, searchDrawableSize)
            } else {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            }
        }
        imeOptions = EditorInfo.IME_ACTION_SEARCH
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (lengthBefore == 0 && lengthAfter > 0) {
            isNeedDraw = true
            invalidate()
        } else if (lengthBefore > 0 && lengthAfter == 0) {
            isNeedDraw = true
            invalidate()
        } else {
            isNeedDraw = false
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isNeedDraw && text.toString().isEmpty()) {
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
            Log.e("TAG", "width: ${bounds.width()} height:${bounds.height()}")

            canvas.save()
            canvas.drawText(
                searchText,
                (width - bounds.width()) / 2F + bounds.width() / 2,
                (height + bounds.height()) / 2F,
                paint
            )
            searchDrawable?.let {
                canvas.translate(
                    (width - searchDrawableSize) / 2F - bounds.width() / 2,
                    (height - searchDrawableSize) / 2F
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