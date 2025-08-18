package com.example.androidui.circleround.view1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val path = Path()

    override fun onDraw(canvas: Canvas?) {
        val radius = minOf(width, height) / 2F
        path.addCircle(width / 2F, height / 2F, radius, Path.Direction.CW)
        canvas?.clipPath(path)
        super.onDraw(canvas)
    }
}