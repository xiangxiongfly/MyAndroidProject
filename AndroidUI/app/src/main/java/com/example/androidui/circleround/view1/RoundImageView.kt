package com.example.androidui.circleround.view1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.core.exts.dp

class RoundImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val path = Path()

    override fun onDraw(canvas: Canvas?) {
         path.addRoundRect(
            RectF(0F, 0F, width.toFloat(), height.toFloat()),
            10F.dp,
            10F.dp,
            Path.Direction.CW
        )
        canvas?.clipPath(path)
        super.onDraw(canvas)
    }
}