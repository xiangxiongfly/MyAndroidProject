package com.example.tools.ratio

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.tools.R


class MyRatioFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var widthRatio: Float = 0F
    private var heightRatio: Float = 0F

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.MyRatioFrameLayout)
        val whRatio = a.getString(R.styleable.MyRatioFrameLayout_whRatio)
        a.recycle()
        whRatio?.let {
            val strs = it.split(":");
            when (strs.size) {
                1 -> {
                    widthRatio = strs[0].toFloat()
                    heightRatio = 1F
                }
                2 -> {
                    widthRatio = strs[0].toFloat()
                    heightRatio = strs[1].toFloat()
                }
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var wMeasureSpec = widthMeasureSpec
        var hMeasureSpec = heightMeasureSpec
        if (widthRatio != 0F && heightRatio != 0F) {
            val ratio = getRatio()
            val lp: ViewGroup.LayoutParams = layoutParams
            val widthMode = MeasureSpec.getMode(widthMeasureSpec)
            val widthSize = MeasureSpec.getSize(widthMeasureSpec)
            val heightMode = MeasureSpec.getMode(heightMeasureSpec)
            val heightSize = MeasureSpec.getSize(heightMeasureSpec)
            if (lp.width != ViewGroup.LayoutParams.WRAP_CONTENT && widthMode == MeasureSpec.EXACTLY &&
                lp.height != ViewGroup.LayoutParams.WRAP_CONTENT && heightMode == MeasureSpec.EXACTLY
            ) {
                // 宽度和高度都是固定值
                if (widthSize / ratio < heightSize) {
                    // 如果计算后高度小于原有高度
                    hMeasureSpec = MeasureSpec.makeMeasureSpec(
                        (widthSize / ratio).toInt(),
                        MeasureSpec.EXACTLY
                    )
                } else if (heightSize * ratio <= widthSize) {
                    // 如果计算后的宽度小于原有宽度
                    wMeasureSpec = MeasureSpec.makeMeasureSpec(
                        (heightSize * ratio).toInt(),
                        MeasureSpec.EXACTLY
                    )
                }
            } else if (lp.width != ViewGroup.LayoutParams.WRAP_CONTENT && widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
                // 宽度固定值
                hMeasureSpec =
                    MeasureSpec.makeMeasureSpec((widthSize / ratio).toInt(), MeasureSpec.EXACTLY)

            } else if (lp.height != ViewGroup.LayoutParams.WRAP_CONTENT && heightMode == MeasureSpec.EXACTLY && widthMode != MeasureSpec.EXACTLY) {
                // 高度固定值
                wMeasureSpec =
                    MeasureSpec.makeMeasureSpec((heightSize * ratio).toInt(), MeasureSpec.EXACTLY)
            }
        }
        super.onMeasure(wMeasureSpec, hMeasureSpec)
    }

    fun setRatio(widthRatio: Float, heightRatio: Float) {
        this.widthRatio = widthRatio
        this.heightRatio = heightRatio
    }

    fun getRatio(): Float {
        return widthRatio / heightRatio
    }
}