package com.example.tools.genBitmap

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.base.BaseActivity
import com.example.tools.R


class GenerateBitmapActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_bitmap)
    }

    fun genBitmap(view: View) {
        val root = findViewById<LinearLayout>(R.id.root)
        val ivImage = findViewById<ImageView>(R.id.iv_image)
        ivImage.setImageBitmap(getBitmapByView(root))
    }

    /**
     * 通过View生成Bitmap
     */
    fun getBitmapByView(view: View?): Bitmap? {
        if (null == view) {
            return null
        }
        var bitmap: Bitmap? = null

        // 步骤一：获取视图的宽和高
        val width: Int = view.getRight() - view.getLeft()
        val height: Int = view.getBottom() - view.getTop()

        // 判断背景是否为不透明
        val opaque = view.getDrawingCacheBackgroundColor() !== 0 || view.isOpaque()
        val quality: Bitmap.Config
        quality = if (!opaque) {
            when (view.getDrawingCacheQuality()) {
                DRAWING_CACHE_QUALITY_AUTO, DRAWING_CACHE_QUALITY_LOW, DRAWING_CACHE_QUALITY_HIGH -> Bitmap.Config.ARGB_8888
                else -> Bitmap.Config.ARGB_8888
            }
        } else {
            Bitmap.Config.RGB_565
        }

        // 步骤二：生成bitmap
        bitmap = Bitmap.createBitmap(resources.displayMetrics, width, height, quality)
        bitmap.density = resources.displayMetrics.densityDpi
        if (opaque) {
            bitmap.setHasAlpha(false)
        }
        val clear = view.getDrawingCacheBackgroundColor() !== 0

        // 步骤三：绘制canvas
        val canvas = Canvas(bitmap)
        if (clear) {
            bitmap.eraseColor(view.getDrawingCacheBackgroundColor())
        }
        view.computeScroll()
        val restoreCount: Int = canvas.save()
        canvas.translate((-view.getScrollX()).toFloat(), (-view.getScrollY()).toFloat())
        view.draw(canvas)
        canvas.restoreToCount(restoreCount)
        canvas.setBitmap(null)
        return bitmap
    }

}