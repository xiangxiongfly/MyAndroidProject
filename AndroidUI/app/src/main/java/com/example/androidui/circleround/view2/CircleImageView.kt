package com.example.androidui.circleround.view2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }

    override fun onDraw(canvas: Canvas) {
        drawable ?: return
        val bitmap = drawableToBitmap(drawable) ?: return
        getShader(bitmap)
        val radius = minOf(width, height) / 2f
        canvas.drawCircle(width / 2F, height / 2F, radius, paint)
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap? {
        return when (drawable) {
            is BitmapDrawable -> drawable.bitmap
            else -> {
                val bitmap = Bitmap.createBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)
                bitmap
            }
        }
    }

    private fun getShader(bitmap: Bitmap) {
        val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val matrix = Matrix()
        val scale: Float
        val dx: Float
        val dy: Float
        if (bitmap.width * height > width * bitmap.height) {
            scale = height / bitmap.height.toFloat()
            dx = (width - bitmap.width * scale) * 0.5f
            dy = 0f
        } else {
            scale = width / bitmap.width.toFloat()
            dx = 0f
            dy = (height - bitmap.height * scale) * 0.5f
        }
        matrix.setScale(scale, scale)
        matrix.postTranslate(dx, dy)
        shader.setLocalMatrix(matrix)
        paint.shader = shader
    }

}
