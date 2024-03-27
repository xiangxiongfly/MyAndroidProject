package com.example.widgets.custom_edittext

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import com.example.widgets.R

/**
 * 可清空内容的EditText
 */
class ClearEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseEditText(context, attrs) {

    private val deleteIconDrawable: Drawable?
    private val tipIconDefaultDrawable: Drawable?
    private val tipIconSelectedDrawable: Drawable?
    private val bgDefaultDrawable: Drawable?
    private val bgSelectedDrawable: Drawable?

    init {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.ClearEditText)
        val deleteIconSize =
            a.getDimensionPixelSize(R.styleable.ClearEditText_cet_deleteIconSize, 0)
        deleteIconDrawable = a.getDrawable(R.styleable.ClearEditText_cet_deleteIcon)
        deleteIconDrawable?.let { it ->
            if (deleteIconSize > 0) {
                it.setBounds(0, 0, deleteIconSize, deleteIconSize)
            } else {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            }
        }
        val tipIconSize = a.getDimensionPixelSize(R.styleable.ClearEditText_cet_tipIconSize, 0)
        tipIconDefaultDrawable = a.getDrawable(R.styleable.ClearEditText_cet_tipDefaultIcon)
        tipIconDefaultDrawable?.let { it ->
            if (tipIconSize > 0) {
                it.setBounds(0, 0, tipIconSize, tipIconSize)
            } else {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            }
        }
        tipIconSelectedDrawable = a.getDrawable(R.styleable.ClearEditText_cet_tipSelectedIcon)
        tipIconSelectedDrawable?.let { it ->
            if (tipIconSize > 0) {
                it.setBounds(0, 0, tipIconSize, tipIconSize)
            } else {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            }
        }
        bgDefaultDrawable = a.getDrawable(R.styleable.ClearEditText_cet_defaultBg)
        bgSelectedDrawable = a.getDrawable(R.styleable.ClearEditText_cet_selectedBg)
        a.recycle()

        setup()
    }

    private fun setup() {
        setDeleteIconVisible(false, false)
        bgDefaultDrawable?.let {
            background = it
        }
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        setDeleteIconVisible(hasFocus() && text.length > 0, hasFocus())
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        setDeleteIconVisible(focused && length() > 0, focused)
    }

    private fun setDeleteIconVisible(deleteVisible: Boolean, focused: Boolean) {
        setCompoundDrawablesRelative(
            if (focused) tipIconSelectedDrawable else tipIconDefaultDrawable,
            null,
            if (deleteVisible) deleteIconDrawable else null,
            null
        )
        if (bgDefaultDrawable != null && bgSelectedDrawable != null) {
            background = if (focused) bgSelectedDrawable else bgDefaultDrawable
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            val drawable = deleteIconDrawable
            if (drawable != null) {
                if (event.x <= width - paddingRight && event.x >= width - paddingRight - drawable.bounds.width()) {
                    text = null
                }
            }
        }
        return super.onTouchEvent(event)
    }
}