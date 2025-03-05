package com.example.widgets.titlebar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.base.utils.dp
import com.example.widgets.R

class TitleBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        const val NONE_RES = -1

        val DEFAULT_VERTICAL_PADDING = 15.dp

        const val DEFAULT_TITLE_POSITION = 3
        const val DEFAULT_TITLE = ""
        const val DEFAULT_TITLE_COLOR = 0xFF222222.toInt()
        val DEFAULT_TITLE_SIZE = 16.dp
        const val DEFAULT_TITLE_STYLE = 1
        const val DEFAULT_TITLE_ICON_SIZE = 0
        const val DEFAULT_TITLE_ICON_PADDING = 0
        const val DEFAULT_TITLE_ICON_POSITION = 1
        const val DEFAULT_TITLE_HORIZONTAL_PADDING = 0
        const val DEFAULT_TITLE_OVERFLOW_MODE = 0

        const val DEFAULT_LEFT_TITLE = ""
        const val DEFAULT_LEFT_TITLE_COLOR = 0xFF666666.toInt()
        val DEFAULT_LEFT_TITLE_SIZE = 14.dp
        const val DEFAULT_LEFT_TITLE_STYLE = 1
        const val DEFAULT_LEFT_ICON_SIZE = 0
        const val DEFAULT_LEFT_ICON_PADDING = 0
        const val DEFAULT_LEFT_ICON_POSITION = 1
        val DEFAULT_LEFT_HORIZONTAL_PADDING = 10.dp

        const val DEFAULT_RIGHT_TITLE = ""
        const val DEFAULT_RIGHT_TITLE_COLOR = 0xFF666666.toInt()
        val DEFAULT_RIGHT_TITLE_SIZE = 14.dp
        const val DEFAULT_RIGHT_TITLE_STYLE = 1
        const val DEFAULT_RIGHT_ICON_SIZE = 0
        const val DEFAULT_RIGHT_ICON_PADDING = 0
        const val DEFAULT_RIGHT_ICON_POSITION = 1
        val DEFAULT_RIGHT_HORIZONTAL_PADDING = 10.dp

        const val DEFAULT_LINE_VISIBLE = true
        const val DEFAULT_LINE_COLOR = 0xFFECECEC.toInt()
        const val DEFAULT_LINE_SIZE = 1
    }

    // 中间TextView
    private val mTitleTextView by lazy { TitleBarFactory.newTitleView(context) }

    // 左边TextView
    private val mLeftTextView by lazy { TitleBarFactory.newLeftView(context) }

    // 右边TextView
    private val mRightTextView by lazy { TitleBarFactory.newRightView(context) }

    // 下划线
    private val mLineView by lazy { TitleBarFactory.newLine(context) }

    init {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        val verticalPadding =
            a.getDimensionPixelSize(R.styleable.TitleBar_verticalPadding, DEFAULT_VERTICAL_PADDING)

        setupTitleView(a, verticalPadding)
        setupLeftView(a, verticalPadding)
        setupRightView(a, verticalPadding)
        setupLineView(a)
        a.recycle()

        addView(mTitleTextView, 0)
        addView(mLeftTextView, 1)
        addView(mRightTextView, 2)
        addView(mLineView, 3)
    }

    /**
     * 设置中间标题区域
     */
    private fun setupTitleView(a: TypedArray, verticalPadding: Int) {
        // 设置标题文字
        val title = a.getString(R.styleable.TitleBar_title) ?: DEFAULT_TITLE
        setTitle(title)
        // 设置标题文字颜色
        val titleColor = a.getColor(R.styleable.TitleBar_titleColor, DEFAULT_TITLE_COLOR)
        mTitleTextView.setTextColor(titleColor)
        // 设置标题文字大小
        val titleSize = a.getDimensionPixelSize(R.styleable.TitleBar_titleSize, DEFAULT_TITLE_SIZE)
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize.toFloat())
        // 设置标题样式
        val titleTypeFace: Typeface =
            when (a.getInt(R.styleable.TitleBar_titleStyle, DEFAULT_TITLE_STYLE)) {
                2 -> Typeface.DEFAULT_BOLD
                3 -> Typeface.MONOSPACE
                else -> Typeface.DEFAULT
            }
        mTitleTextView.typeface = titleTypeFace
        // 设置标题文字位置
        val titlePosition = a.getInt(R.styleable.TitleBar_titlePosition, DEFAULT_TITLE_POSITION)
        val lp = mTitleTextView.layoutParams as LayoutParams
        lp.gravity = when (titlePosition) {
            1 -> Gravity.LEFT
            2 -> Gravity.RIGHT
            3 -> Gravity.CENTER
            else -> Gravity.NO_GRAVITY
        }
        mTitleTextView.layoutParams = lp
        // 设置标题图标
        if (a.hasValue(R.styleable.TitleBar_titleIcon)) {
            val titleIconRes = a.getResourceId(R.styleable.TitleBar_titleIcon, -1)
            val titleDrawable = ContextCompat.getDrawable(context, titleIconRes)
            val titleIconSize =
                a.getDimensionPixelSize(R.styleable.TitleBar_titleIconSize, DEFAULT_TITLE_ICON_SIZE)
            val titleIconPadding = a.getDimensionPixelSize(
                R.styleable.TitleBar_titleIconPadding, DEFAULT_TITLE_ICON_PADDING
            )
            val titleIconPosition =
                a.getInt(R.styleable.TitleBar_titleIconPosition, DEFAULT_TITLE_ICON_POSITION)
            if (titleDrawable != null) {
                // 设置图标大小
                if (titleIconSize > 0) {
                    titleDrawable.setBounds(0, 0, titleIconSize, titleIconSize)
                } else {
                    titleDrawable.setBounds(
                        0,
                        0,
                        titleDrawable.intrinsicWidth,
                        titleDrawable.intrinsicHeight
                    )
                }
                // 设置图标距离文字
                mTitleTextView.compoundDrawablePadding = titleIconPadding
                // 设置图标位置
                when (titleIconPosition) {
                    1 -> mTitleTextView.setCompoundDrawables(titleDrawable, null, null, null)
                    2 -> mTitleTextView.setCompoundDrawables(null, null, titleDrawable, null)
                    3 -> mTitleTextView.setCompoundDrawables(null, titleDrawable, null, null)
                    4 -> mTitleTextView.setCompoundDrawables(null, null, null, titleDrawable)
                }
            }
        }
        // 设置标题的水平间距
        val titleHorizontalPadding = a.getDimensionPixelSize(
            R.styleable.TitleBar_titleHorizontalPadding, DEFAULT_TITLE_HORIZONTAL_PADDING
        )
        // 设置标题边距
        mTitleTextView.setPadding(
            titleHorizontalPadding,
            verticalPadding,
            titleHorizontalPadding,
            verticalPadding
        )
        // 设置标题文字溢出模式
        val titleOverflowMode =
            a.getInt(R.styleable.TitleBar_titleOverflowMode, DEFAULT_TITLE_OVERFLOW_MODE)
        mTitleTextView.ellipsize = when (titleOverflowMode) {
            1 -> TextUtils.TruncateAt.START
            2 -> TextUtils.TruncateAt.MIDDLE
            3 -> TextUtils.TruncateAt.END
            4 -> {
                // 设置跑马灯之后需要设置选中才能有效果
                mTitleTextView.isSelected = true
                // 设置跑马灯需要先获取焦点
                mTitleTextView.isFocusable = true
                // 设置跑马灯的循环次数
                mTitleTextView.marqueeRepeatLimit = -1
                TextUtils.TruncateAt.MARQUEE
            }
            else -> null
        }
        // 设置跑马灯的循环次数
        mTitleTextView.marqueeRepeatLimit = -1
    }

    /**
     * 设置左边区域
     */
    private fun setupLeftView(a: TypedArray, verticalPadding: Int) {
        // 设置左边标题
        val leftText = a.getString(R.styleable.TitleBar_leftText) ?: DEFAULT_LEFT_TITLE
        setLeftText(leftText)
        // 设置左边标题颜色
        val leftTitleColor =
            a.getColor(R.styleable.TitleBar_leftTextColor, DEFAULT_LEFT_TITLE_COLOR)
        mLeftTextView.setTextColor(leftTitleColor)
        // 设置左边标题大小
        val leftTitleSize =
            a.getDimensionPixelSize(R.styleable.TitleBar_leftTextSize, DEFAULT_LEFT_TITLE_SIZE)
        mLeftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTitleSize.toFloat())
        // 设置左边标题样式
        val leftTitleTypeFace: Typeface =
            when (a.getInt(R.styleable.TitleBar_leftTextStyle, DEFAULT_LEFT_TITLE_STYLE)) {
                2 -> Typeface.DEFAULT_BOLD
                3 -> Typeface.MONOSPACE
                else -> Typeface.DEFAULT
            }
        mLeftTextView.typeface = leftTitleTypeFace
        // 设置左边图标
        if (a.hasValue(R.styleable.TitleBar_leftIcon)) {
            val leftIconRes = a.getResourceId(R.styleable.TitleBar_leftIcon, NONE_RES)
            val leftDrawable = ContextCompat.getDrawable(context, leftIconRes)
            val leftIconSize =
                a.getDimensionPixelSize(R.styleable.TitleBar_leftIconSize, DEFAULT_LEFT_ICON_SIZE)
            val leftIconPadding = a.getDimensionPixelSize(
                R.styleable.TitleBar_leftIconPadding, DEFAULT_LEFT_ICON_PADDING
            )
            val leftIconPosition =
                a.getInt(R.styleable.TitleBar_leftIconPosition, DEFAULT_LEFT_ICON_POSITION)
            if (leftDrawable != null) {
                // 设置图标大小
                if (leftIconSize > 0) {
                    leftDrawable.setBounds(0, 0, leftIconSize, leftIconSize)
                } else {
                    leftDrawable.setBounds(
                        0,
                        0,
                        leftDrawable.intrinsicWidth,
                        leftDrawable.intrinsicHeight
                    )
                }
                // 设置图标距离文字
                mLeftTextView.compoundDrawablePadding = leftIconPadding
                // 设置图标位置
                when (leftIconPosition) {
                    1 -> mLeftTextView.setCompoundDrawables(leftDrawable, null, null, null)
                    2 -> mLeftTextView.setCompoundDrawables(null, null, leftDrawable, null)
                    3 -> mLeftTextView.setCompoundDrawables(null, leftDrawable, null, null)
                    4 -> mLeftTextView.setCompoundDrawables(null, null, null, leftDrawable)
                }
            }
        }
        // 设置左边的水平间距
        val leftHorizontalPadding = a.getDimensionPixelSize(
            R.styleable.TitleBar_leftHorizontalPadding, DEFAULT_LEFT_HORIZONTAL_PADDING
        )
        mLeftTextView.setPadding(
            leftHorizontalPadding,
            verticalPadding,
            leftHorizontalPadding,
            verticalPadding
        )
    }

    /**
     * 设置右边区域
     */
    private fun setupRightView(a: TypedArray, verticalPadding: Int) {
        // 设置右边标题
        val rightText = a.getString(R.styleable.TitleBar_rightText) ?: DEFAULT_RIGHT_TITLE
        setRightText(rightText)
        // 设置右边标题颜色
        val rightTitleColor =
            a.getColor(R.styleable.TitleBar_rightTextColor, DEFAULT_RIGHT_TITLE_COLOR)
        mRightTextView.setTextColor(rightTitleColor)
        // 设置右边标题大小
        val rightTitleSize =
            a.getDimensionPixelSize(R.styleable.TitleBar_rightTextSize, DEFAULT_RIGHT_TITLE_SIZE)
        mRightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTitleSize.toFloat())
        // 设置右边标题样式
        val rightTitleTypeFace: Typeface =
            when (a.getInt(R.styleable.TitleBar_rightTextStyle, DEFAULT_RIGHT_TITLE_STYLE)) {
                2 -> Typeface.DEFAULT_BOLD
                3 -> Typeface.MONOSPACE
                else -> Typeface.DEFAULT
            }
        mRightTextView.typeface = rightTitleTypeFace
        // 设置右边图标
        if (a.hasValue(R.styleable.TitleBar_rightIcon)) {
            val rightIconRes = a.getResourceId(R.styleable.TitleBar_rightIcon, NONE_RES)
            val rightDrawable = ContextCompat.getDrawable(context, rightIconRes)
            val rightIconSize =
                a.getDimensionPixelSize(R.styleable.TitleBar_rightIconSize, DEFAULT_RIGHT_ICON_SIZE)
            val rightIconPadding = a.getDimensionPixelSize(
                R.styleable.TitleBar_rightIconPadding, DEFAULT_RIGHT_ICON_PADDING
            )
            val rightIconPosition =
                a.getInt(R.styleable.TitleBar_rightIconPosition, DEFAULT_RIGHT_ICON_POSITION)
            if (rightDrawable != null) {
                // 设置图标大小
                if (rightIconSize > 0) {
                    rightDrawable.setBounds(0, 0, rightIconSize, rightIconSize)
                } else {
                    rightDrawable.setBounds(
                        0,
                        0,
                        rightDrawable.intrinsicWidth,
                        rightDrawable.intrinsicHeight
                    )
                }
                // 设置图标距离文字
                mRightTextView.compoundDrawablePadding = rightIconPadding
                // 设置图标位置
                when (rightIconPosition) {
                    1 -> mRightTextView.setCompoundDrawables(rightDrawable, null, null, null)
                    2 -> mRightTextView.setCompoundDrawables(null, null, rightDrawable, null)
                    3 -> mRightTextView.setCompoundDrawables(null, rightDrawable, null, null)
                    4 -> mRightTextView.setCompoundDrawables(null, null, null, rightDrawable)
                }
            }
        }
        // 设置右边的水平间距
        val rightHorizontalPadding = a.getDimensionPixelSize(
            R.styleable.TitleBar_rightHorizontalPadding, DEFAULT_RIGHT_HORIZONTAL_PADDING
        )
        mRightTextView.setPadding(
            rightHorizontalPadding,
            verticalPadding,
            rightHorizontalPadding,
            verticalPadding
        )
    }

    /**
     * 设置下划线
     */
    private fun setupLineView(a: TypedArray) {
        // 设置下划线可见
        val lineVisible = a.getBoolean(R.styleable.TitleBar_lineVisible, DEFAULT_LINE_VISIBLE)
        mLineView.visibility = if (lineVisible) View.VISIBLE else View.GONE
        // 设置下划线颜色
        mLineView.background =
            if (a.hasValue(R.styleable.TitleBar_lineColor)) {
                a.getDrawable(R.styleable.TitleBar_lineColor) ?: ColorDrawable(DEFAULT_LINE_COLOR)
            } else {
                ColorDrawable(DEFAULT_LINE_COLOR)
            }
        // 设置下划线尺寸
        val lineSize = a.getDimensionPixelSize(R.styleable.TitleBar_lineSize, DEFAULT_LINE_SIZE)
        mLineView.apply {
            val lp = layoutParams.apply {
                height = lineSize
            }
            layoutParams = lp
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val titleBarMeasuredWidth = measuredWidth
        val titleBarMeasuredHeight = measuredHeight
        val titleViewMeasuredWidth = mTitleTextView.measuredWidth
        val titleViewMeasuredHeight = mTitleTextView.measuredHeight
        val leftViewMeasuredWidth = mLeftTextView.measuredWidth
        val leftViewMeasuredHeight = mLeftTextView.measuredHeight
        val rightViewMeasuredWidth = mRightTextView.measuredWidth
        val rightViewMeasuredHeight = mRightTextView.measuredHeight
        val maxEdgeWidth = Math.max(leftViewMeasuredWidth, rightViewMeasuredWidth)
        val totalWidth = titleViewMeasuredWidth + maxEdgeWidth * 2

        if (totalWidth <= titleBarMeasuredWidth) {
            // 测量的总宽度小于标题栏宽度
            measureTitleBar(
                if (isContainView(mLeftTextView)) leftViewMeasuredWidth else 0,
                titleViewMeasuredWidth,
                if (isContainView(mRightTextView)) rightViewMeasuredWidth else 0,
                heightMeasureSpec
            )
        } else {
            // 测量的总宽度大于标题栏宽度
            var leftViewWidth = 0
            var rightViewWidth = 0
            var titleViewWidth = 0
            if (maxEdgeWidth > titleBarMeasuredWidth / 3) {
                // 左右项太长
                leftViewWidth = titleBarMeasuredWidth / 4 // 左右项各占25%
                rightViewWidth = leftViewWidth
                titleViewWidth = titleBarMeasuredWidth / 2 // 中间标题占50%
            } else {
                // 中间标题太长
                leftViewWidth = maxEdgeWidth // 左右项占maxEdgeWidth
                rightViewWidth = leftViewWidth
                titleViewWidth = titleBarMeasuredWidth - maxEdgeWidth * 2 // 中间标题占剩余空间
            }
            measureTitleBar(
                if (isContainView(mLeftTextView)) leftViewWidth else 0,
                titleViewWidth,
                if (isContainView(mRightTextView)) rightViewWidth else 0,
                heightMeasureSpec
            )
        }
    }

    /**
     * 测量
     */
    private fun measureTitleBar(
        leftViewWidth: Int,
        titleViewWidth: Int,
        rightViewWidth: Int,
        titleBarHeightMeasureSpec: Int
    ) {
        val leftWidthMeasureSpec = MeasureSpec.makeMeasureSpec(leftViewWidth, MeasureSpec.EXACTLY)
        val titleWidthMeasureSpec = MeasureSpec.makeMeasureSpec(titleViewWidth, MeasureSpec.EXACTLY)
        val rightWidthMeasureSpec = MeasureSpec.makeMeasureSpec(rightViewWidth, MeasureSpec.EXACTLY)
        measureChildWithMargins(
            mLeftTextView,
            leftWidthMeasureSpec, 0,
            titleBarHeightMeasureSpec, 0
        )
        measureChildWithMargins(
            mTitleTextView,
            titleWidthMeasureSpec, 0,
            titleBarHeightMeasureSpec, 0
        )
        measureChildWithMargins(
            mRightTextView,
            rightWidthMeasureSpec, 0,
            titleBarHeightMeasureSpec, 0
        )
    }

    /**
     * 判断TextView是否存在内容
     * 返回true，表示存在；
     * 返回false，表示不存在。
     */
    private fun isContainView(textView: TextView): Boolean {
        val text = textView.text
        if (!TextUtils.isEmpty(text)) {
            return true
        }
        val drawables = textView.compoundDrawables
        for (d in drawables) {
            if (d != null) {
                return true
            }
        }
        return false
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams) {
        if (params.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
        }
        if (params.height > 0) {
            mTitleTextView.setPadding(mTitleTextView.paddingLeft, 0, mTitleTextView.paddingRight, 0)
            mLeftTextView.setPadding(mTitleTextView.paddingLeft, 0, mTitleTextView.paddingRight, 0)
            mRightTextView.setPadding(mTitleTextView.paddingLeft, 0, mTitleTextView.paddingRight, 0)
        }
        super.setLayoutParams(params)
    }

    /**
     * 获取左边视图
     */
    fun getLeftView(): TextView {
        return mLeftTextView
    }

    /**
     * 获取右边视图
     */
    fun getRightView(): TextView {
        return mRightTextView
    }

    /**
     * 获取标题视图
     */
    fun getTitleView(): TextView {
        return mTitleTextView
    }

    /**
     * 设置左边点击事件
     */
    fun setOnLeftClickListener(listener: OnClickListener): TitleBar {
        mLeftTextView.setOnClickListener(listener)
        return this
    }

    /**
     * 设置右边点击事件
     */
    fun setOnTitleClickListener(listener: OnClickListener): TitleBar {
        mTitleTextView.setOnClickListener(listener)
        return this
    }

    /**
     * 设置标题点击事件
     */
    fun setOnRightClickListener(listener: OnClickListener): TitleBar {
        mRightTextView.setOnClickListener(listener)
        return this
    }

    /**
     * 设置标题
     */
    fun setTitle(title: String): TitleBar {
        mTitleTextView.text = title
        return this
    }

    /**
     * 设置左边文本
     */
    fun setLeftText(leftText: String): TitleBar {
        mLeftTextView.text = leftText
        return this
    }

    /**
     * 设置右边文本
     */
    fun setRightText(rightText: String): TitleBar {
        mRightTextView.text = rightText
        return this
    }
}