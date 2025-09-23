package com.xiangxiongfly.core.widgets.bubble

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.xiangxiongfly.core.R
import com.xiangxiongfly.core.utils.dp

class BubbleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val path = Path()
    private var triangleWidth = DEFAULT_TRIANGLE_WIDTH // 气泡三角形的宽度
    private var triangleHeight = DEFAULT_TRIANGLE_HEIGHT // 气泡三角形的高度
    private var triangleTop = DEFAULT_TRIANGLE_TOP // 气泡三角形距离顶部距离
    private var direction = DEFAULT_DIRECTION // 气泡方向
    private var radius = DEFAULT_RADIUS // 圆角

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.BubbleImageView)
        triangleWidth = a.getDimension(
            R.styleable.BubbleImageView_triangle_width,
            DEFAULT_TRIANGLE_WIDTH
        )
        triangleHeight = a.getDimension(
            R.styleable.BubbleImageView_triangle_height,
            DEFAULT_TRIANGLE_HEIGHT
        )
        triangleTop = a.getDimension(
            R.styleable.BubbleImageView_triangle_top,
            DEFAULT_TRIANGLE_TOP
        )
        direction = a.getInt(
            R.styleable.BubbleImageView_direction,
            DEFAULT_DIRECTION
        )
        radius = a.getDimension(
            R.styleable.BubbleImageView_radius,
            DEFAULT_RADIUS
        )
        a.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        path.reset()
        when (direction) {
            LEFT -> {
                // 绘制三角形
                path.moveTo(triangleWidth, triangleTop)
                path.lineTo(0F, triangleTop + triangleHeight / 2)
                path.lineTo(triangleWidth, triangleTop + triangleHeight)
                path.close()
                // 绘制圆角矩形
                path.addRoundRect(
                    triangleWidth,
                    0F,
                    measuredWidth.toFloat(),
                    measuredHeight.toFloat(),
                    radius,
                    radius,
                    Path.Direction.CW
                )
            }

            RIGHT -> {
                path.moveTo(measuredWidth - triangleWidth, triangleTop)
                path.lineTo(measuredWidth.toFloat(), triangleTop + triangleHeight / 2)
                path.lineTo(measuredWidth - triangleWidth, triangleTop + triangleHeight)
                path.close()
                path.addRoundRect(
                    0F,
                    0F,
                    measuredWidth - triangleWidth,
                    measuredHeight.toFloat(),
                    radius,
                    radius,
                    Path.Direction.CW
                )
            }
        }
        canvas.clipPath(path)
        super.onDraw(canvas)
    }

    companion object {
        private const val LEFT = 1
        private const val RIGHT = 2
        private val DEFAULT_RADIUS = 10F.dp
        private val DEFAULT_TRIANGLE_WIDTH = 10F.dp
        private val DEFAULT_TRIANGLE_HEIGHT = 10F.dp
        private val DEFAULT_TRIANGLE_TOP = 20F.dp
        private const val DEFAULT_DIRECTION = LEFT
    }
}