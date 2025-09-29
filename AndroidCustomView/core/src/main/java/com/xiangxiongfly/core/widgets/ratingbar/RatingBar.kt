package com.xiangxiongfly.core.widgets.ratingbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.xiangxiongfly.core.R
import kotlin.math.ceil

class RatingBar @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    // 默认图标
    private var normalDrawable: Drawable

    // 选中图标
    private var selectedDrawable: Drawable

    // 当前等级
    private var currentStar = DEFAULT_STAR

    // 总等级
    private var starCount = DEFAULT_STAR_COUNT

    // 图标宽度
    private var starWidth = 0F

    // 图标高度
    private var starHeight = 0F

    // 图标间距
    private var starGap = 0F

    private val rect = Rect()

    private var onRatingBarChange: ((Int, Boolean ) -> Unit)? = null

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar)
        normalDrawable =
            (typedArray.getDrawable(R.styleable.RatingBar_normalDrawable)
                ?: ContextCompat.getDrawable(context, R.drawable.ic_star_normal))!!
        selectedDrawable =
            (typedArray.getDrawable(R.styleable.RatingBar_selectedDrawable)
                ?: ContextCompat.getDrawable(context, R.drawable.ic_star_selected))!!
        if (normalDrawable.intrinsicWidth != selectedDrawable.intrinsicWidth ||
            normalDrawable.intrinsicHeight != selectedDrawable.intrinsicHeight
        ) {
            throw IllegalStateException("2张图片的尺寸不一样")
        }
        currentStar = typedArray.getInt(R.styleable.RatingBar_star, DEFAULT_STAR)
        starCount = typedArray.getInt(R.styleable.RatingBar_starCount, DEFAULT_STAR_COUNT)
        starWidth = typedArray.getDimension(
            R.styleable.RatingBar_starWidth,
            normalDrawable.intrinsicWidth.toFloat()
        )
        starHeight = typedArray.getDimension(
            R.styleable.RatingBar_starHeight,
            normalDrawable.intrinsicHeight.toFloat()
        )
        starGap = typedArray.getDimension(
            R.styleable.RatingBar_starGap,
            starWidth / 4F
        )
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measuredWidth = (starWidth * starCount) + (starGap * (starCount - 1))
        val measuredHeight = starHeight
        setMeasuredDimension(measuredWidth.toInt(), measuredHeight.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        for (index in 0..starCount - 1) {
            if (index == 0) {
                rect.left = paddingLeft
            } else {
                rect.left = (rect.right + starGap).toInt()
            }
            rect.top = paddingTop
            rect.right = (rect.left + starWidth).toInt()
            rect.bottom = (rect.top + starHeight).toInt()
            if (currentStar > index) {
                selectedDrawable.bounds = rect
                selectedDrawable.draw(canvas)
            } else {
                normalDrawable.bounds = rect
                normalDrawable.draw(canvas)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled) {
            return false
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_MOVE -> {
                val distance = event.x - paddingLeft
                if (distance < 0) {
                    currentStar = 0
                } else {
                    val star = ceil(distance / (starWidth + starGap)).toInt()
                    if (currentStar != star) {
                        currentStar = star
                        invalidate()
                        onRatingBarChange?.invoke(currentStar, true)
                    }
                }
            }
        }
        return true
    }

    fun getCurrentStar() = currentStar

    fun setStar(star: Int) {
        currentStar = star
        invalidate()
        onRatingBarChange?.invoke(currentStar, false)
    }

    fun setOnRatingBarChange(onChange: (Int, Boolean) -> Unit) {
        this.onRatingBarChange = onChange
    }

    companion object {
        private const val DEFAULT_STAR = 0
        private const val DEFAULT_STAR_COUNT = 5
    }
}