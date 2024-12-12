package com.example.widgets.customedittext

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import com.example.widgets.R

/**
 * 可显示密码的EditText
 */
class PasswordEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseEditText(context, attrs) {

    private val eyeOpenDrawable: Drawable?
    private val eyeCloseDrawable: Drawable?
    private var currentEyeDrawable: Drawable? = null
    private var tipIconDefaultDrawable: Drawable?
    private var tipIconSelectedDrawable: Drawable?
    private var bgDefaultDrawable: Drawable?
    private var bgSelectedDrawable: Drawable?

    init {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.PasswordEditText)

        val eyeIconSize = a.getDimensionPixelSize(R.styleable.PasswordEditText_pet_eyeIconSize, 0)
        eyeOpenDrawable = ContextCompat.getDrawable(context, R.drawable.eye_open)
        eyeOpenDrawable?.let {
            if (eyeIconSize > 0) {
                it.setBounds(0, 0, eyeIconSize, eyeIconSize)
            } else {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            }
        }
        eyeCloseDrawable = ContextCompat.getDrawable(context, R.drawable.eye_close)
        eyeCloseDrawable?.let {
            if (eyeIconSize > 0) {
                it.setBounds(0, 0, eyeIconSize, eyeIconSize)
            } else {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            }
        }
        val tipIconSize = a.getDimensionPixelSize(R.styleable.PasswordEditText_pet_tipIconSize, 0)
        tipIconDefaultDrawable = a.getDrawable(R.styleable.PasswordEditText_pet_tipDefaultIcon)
        tipIconDefaultDrawable?.let { it ->
            if (tipIconSize > 0) {
                it.setBounds(0, 0, tipIconSize, tipIconSize)
            } else {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            }
        }
        tipIconSelectedDrawable = a.getDrawable(R.styleable.PasswordEditText_pet_tipSelectedIcon)
        tipIconSelectedDrawable?.let { it ->
            if (tipIconSize > 0) {
                it.setBounds(0, 0, tipIconSize, tipIconSize)
            } else {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            }
        }
        bgDefaultDrawable = a.getDrawable(R.styleable.PasswordEditText_pet_defaultBg)
        bgSelectedDrawable = a.getDrawable(R.styleable.PasswordEditText_pet_selectedBg)
        a.recycle()

        setup()
    }

    private fun setup() {
        setIconVisible(false, false)
        currentEyeDrawable = eyeCloseDrawable
        bgDefaultDrawable?.let {
            background = it
        }
        inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        transformationMethod = PasswordTransformationMethod.getInstance()
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        setIconVisible(hasFocus() && text.length > 0, hasFocus())
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        setIconVisible(focused && length() > 0, focused)
    }

    private fun setIconVisible(pwdIconVisible: Boolean, focused: Boolean) {
        setCompoundDrawablesRelative(
            if (focused) tipIconSelectedDrawable else tipIconDefaultDrawable,
            null,
            if (pwdIconVisible) currentEyeDrawable else null,
            null
        )
        if (bgDefaultDrawable != null && bgSelectedDrawable != null) {
            background = if (focused) bgSelectedDrawable else bgDefaultDrawable
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            val drawable = currentEyeDrawable
            if (drawable != null) {
                if (event.x <= width - paddingRight && event.x >= width - paddingRight - drawable.bounds.width()) {
                    if (drawable == eyeOpenDrawable) {
                        // 密码不可见
                        currentEyeDrawable = eyeCloseDrawable
                        transformationMethod = PasswordTransformationMethod.getInstance()
                        refreshDrawables()
                    } else if (drawable == eyeCloseDrawable) {
                        // 密码可见
                        currentEyeDrawable = eyeOpenDrawable
                        transformationMethod = HideReturnsTransformationMethod.getInstance()
                        refreshDrawables()
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun refreshDrawables() {
        val drawables = compoundDrawablesRelative
        setCompoundDrawablesRelative(drawables[0], drawables[1], currentEyeDrawable, drawables[3])
    }
}