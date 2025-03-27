package com.xiangxiongfly.common.widgets.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.xiangxiongfly.common.R
import java.io.File
import java.io.FileOutputStream

class SignatureView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    companion object {
        const val DEFAULT_BG_COLOR = Color.WHITE
        const val DEFAULT_BRUSH_COLOR = Color.BLACK
        const val DEFAULT_BRUSH_WIDTH = 12F
    }

    private var bgColor: Int = DEFAULT_BG_COLOR
    private var brushColor: Int = DEFAULT_BRUSH_COLOR
    private var brushWidth = DEFAULT_BRUSH_WIDTH

    private val paint = Paint().apply {
        isAntiAlias = true
        color = brushColor
        style = Paint.Style.STROKE
        strokeWidth = brushWidth
    }

    private val path = Path()
    private var touchX: Float = 0F
    private var touchY: Float = 0F
    private var isClear = false

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SignatureView)
        bgColor = typedArray.getColor(R.styleable.SignatureView_bgColor, DEFAULT_BG_COLOR)
        brushColor = typedArray.getColor(R.styleable.SignatureView_brushColor, DEFAULT_BRUSH_COLOR)
        brushWidth =
            typedArray.getDimension(R.styleable.SignatureView_brushWidth, DEFAULT_BRUSH_WIDTH)
        typedArray.recycle()

        paint.apply {
            color = brushColor
            strokeWidth = brushWidth
        }
        setBackgroundColor(bgColor)
    }

    /**
     * 保存图片
     *
     * @param imageDirs 目录
     * @param imageName 文件名
     * @param clearBlank 是否清除空白区域
     * @param blank 边距
     */
    fun save(imageDirs: String, imageName: String, clearBlank: Boolean = false, blank: Int = 0) {
        if (imageDirs.isEmpty() || imageName.isEmpty()) {
            return
        }
        if (path.isEmpty) {
            return
        }
        var bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(bgColor)
        canvas.drawPath(path, paint)
        if (clearBlank) {
            bitmap = clearBlank(bitmap, blank)
        }
        val dir = File(imageDirs)
        if (!dir.exists())
            dir.mkdirs()
        val file = File(dir, imageName)
        if (file.exists())
            file.delete()
        val out = FileOutputStream(file)
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            out.close()
        }
    }

    /**
     * 清除空白区域
     *
     * @param bitmap 原图
     * @param blank 保留边距
     * @return
     */
    private fun clearBlank(bitmap: Bitmap, blank: Int): Bitmap {
        var space = blank
        val width = bitmap.width
        val height = bitmap.height
        var left = 0
        var right = 0
        var top = 0
        var bottom = 0
        var pixs = IntArray(width)
        var isStop = false
        //扫描上边距不等于背景颜色的第一个点
        for (i in 0 until height) {
            bitmap.getPixels(pixs, 0, width, 0, i, width, 1)
            isStop = false
            for (pix in pixs) {
                if (pix != bgColor) {
                    top = i
                    isStop = true
                    break
                }
            }
            if (isStop) {
                break
            }
        }
        //扫描下边距不等于背景颜色的第一个点
        for (i in height - 1 downTo 0) {
            bitmap.getPixels(pixs, 0, width, 0, i, width, 1)
            isStop = false
            for (pix in pixs) {
                if (pix != bgColor) {
                    bottom = i
                    isStop = true
                    break
                }
            }
            if (isStop) {
                break
            }
        }
        pixs = IntArray(height)
        //扫描左边距不等于背景颜色的第一个点
        for (x in 0 until width) {
            bitmap.getPixels(pixs, 0, 1, x, 0, 1, height)
            isStop = false
            for (pix in pixs) {
                if (pix != bgColor) {
                    left = x
                    isStop = true
                    break
                }
            }
            if (isStop) {
                break
            }
        }
        //扫描右边距不等于背景颜色的第一个点
        for (x in width - 1 downTo 1) {
            bitmap.getPixels(pixs, 0, 1, x, 0, 1, height)
            isStop = false
            for (pix in pixs) {
                if (pix != bgColor) {
                    right = x
                    isStop = true
                    break
                }
            }
            if (isStop) {
                break
            }
        }
        if (space < 0) {
            space = 0
        }
        //计算加上保留空白距离之后的图像大小
        left = Math.max(left - space, 0)
        top = Math.max(top - space, 0)
        right = Math.min(right + space, width - 1)
        bottom = Math.min(bottom + space, height - 1)
        return Bitmap.createBitmap(bitmap, left, top, right - left, bottom - top)
    }

    /**
     * 清除画板
     */
    fun clear() {
        isClear = true
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchX = event.x
                touchY = event.y
                path.moveTo(touchX, touchY)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val x = event.x
                val y = event.y
                Log.e("TAG", "moveX:$x moveY:$y")
                // 计算滑动时偏移值
                val dx = Math.abs(x - touchX)
                val dy = Math.abs(y - touchY)
                // 偏移值大于3px绘制
                if (dx >= 3 || dy >= 3) {
                    val cx = (x + touchX) / 2
                    val cy = (y + touchY) / 2
                    path.quadTo(touchX, touchY, cx, cy)
                    touchX = x
                    touchY = y
                }
                invalidate()
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isClear) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            canvas.drawColor(bgColor)
            path.reset()
            isClear = false
        } else {
            if (!path.isEmpty) {
                canvas.drawPath(path, paint)
            }
        }
    }
}