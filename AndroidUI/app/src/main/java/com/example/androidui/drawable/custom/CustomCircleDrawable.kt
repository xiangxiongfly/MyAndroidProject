package com.example.androidui.drawable.custom

import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import com.example.core.utils.dp
import kotlin.math.min


class CustomCircleDrawable : Drawable(), Animatable {

    private val CIRCLE_WIDTH = 5.dp.toFloat()

    private val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = CIRCLE_WIDTH
        color = Color.GRAY
    }

    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = CIRCLE_WIDTH
        strokeCap = Paint.Cap.ROUND
        color = Color.RED
    }

    private var progress = 0F

    private val valueAnimator = ValueAnimator.ofFloat(0F, 1F).apply {
        duration = 3000L
        repeatCount = Animation.INFINITE
        interpolator = LinearInterpolator()
        addUpdateListener {
            //[0.0.~1.0]
            progress = it.animatedValue as Float
            invalidateSelf()
        }
    }

    init {
        start()
    }

    override fun draw(canvas: Canvas) {
        var radius = (min(bounds.width(), bounds.height()) / 2).toFloat()
        radius -= (bgPaint.strokeWidth / 2)
        val centerX = bounds.exactCenterX()
        val centerY = bounds.exactCenterY()
        val rectF = RectF().apply {
            left = centerX - radius
            right = centerX + radius
            top = centerY - radius
            bottom = centerY + radius
        }
        canvas.rotate(360F * progress, centerX, centerY)
        canvas.drawArc(rectF, 0F, 360F, false, bgPaint)
        canvas.drawArc(rectF, -90F, 360F * progress, false, progressPaint)
    }

    override fun setAlpha(alpha: Int) {
        bgPaint.alpha = alpha
        invalidateSelf()
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        bgPaint.colorFilter = colorFilter
        invalidateSelf()
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun start() {
        if (valueAnimator.isRunning) return
        valueAnimator.start()
    }

    override fun stop() {
        if (valueAnimator.isRunning) valueAnimator.cancel()
    }

    override fun isRunning(): Boolean {
        return valueAnimator.isRunning
    }
}