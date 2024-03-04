package com.example.tools.dialog.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.annotation.FloatRange
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDialog
import com.example.tools.R

/**
 * BaseDialog
 */
class BaseDialog(context: Context, private val params: DialogParams) :
    AppCompatDialog(context, R.style.ToolsBaseDialogStyle) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(params.mDialogView)
        if (params.mWidth == FrameLayout.LayoutParams.WRAP_CONTENT) {
            params.mWidth = params.mDialogView.layoutParams.width
        }
        if (params.mHeight == FrameLayout.LayoutParams.WRAP_CONTENT) {
            params.mHeight = params.mDialogView.layoutParams.height
        }
        window?.let { w ->
            val layoutParams = w.attributes
            layoutParams.apply {
                width = params.mWidth
                if (width > 0) {
                    width -= (params.mGap * 2)
                }
                height = params.mHeight
                gravity = params.mGravity
                windowAnimations = params.mAnimationStyle
                if (params.mDimEnabled) {
                    w.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                    dimAmount = params.mDimAmount
                } else {
                    w.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                }
            }
            w.attributes = layoutParams
        }
        setCanceledOnTouchOutside(params.mCanceledOnTouchOutside) //点击外部区域是否取消
        setCancelable(params.mCancelable) //按返回键是否取消
    }

    class DialogParams {
        lateinit var mDialogView: View

        @FloatRange(from = 0.0, to = 1.0)
        var mDimAmount = 0.6F // 背景暗淡度
        var mDimEnabled = true // 是否显示背景暗淡度
        var mGravity = Gravity.CENTER //显示位置
        var mWidth: Int = ViewGroup.LayoutParams.WRAP_CONTENT // 宽度
        var mHeight: Int = ViewGroup.LayoutParams.WRAP_CONTENT // 高度
        var mGap: Int = 0 // 左右间隙
        var mCancelable: Boolean = true // 点击返回键是否隐藏
        var mCanceledOnTouchOutside: Boolean = true // 点击外部区域是否隐藏

        @StyleRes
        var mAnimationStyle: Int = -1
    }

    abstract class Builder(val context: Context, @LayoutRes layoutResId: Int) {
        private var params: DialogParams = DialogParams()
        private var mDialog: BaseDialog? = null
        private var mActivity: Activity? = null

        val mDialogView: View by lazy {
            LayoutInflater.from(context).inflate(layoutResId, FrameLayout(context), false)
        }
        val mViewHolder: ViewHolder by lazy {
            ViewHolder.create(mDialogView)
        }

        init {
            params.mDialogView = mDialogView
            mActivity = getActivity(context)
        }

        fun getActivity(context: Context): Activity? {
            if (context is Activity) {
                return context
            }
            return null
        }

        /**
         * 是否显示背景暗淡度
         */
        fun setDimEnable(enable: Boolean): Builder {
            params.mDimEnabled = enable
            return this
        }

        /**
         * 设置背景暗淡度
         */
        fun setDimAmount(@FloatRange(from = 0.0, to = 1.0) dimAmount: Float): Builder {
            params.mDimAmount = dimAmount
            return this
        }

        /**
         * 设置显示位置
         */
        fun setGravity(gravity: Int): Builder {
            params.mGravity = gravity
            return this
        }

        /**
         * 设置宽高
         */
        fun setWidth(width: Int): Builder {
            params.mWidth = width
            return this
        }

        fun setHeight(height: Int): Builder {
            params.mHeight = height
            return this
        }

        /**
         * 设置左右空白
         */
        fun setHorizontalGap(gap: Int): Builder {
            params.mGap = gap
            return this
        }

        /**
         * 动画
         */
        fun setAnimationStyle(@StyleRes animationStyle: Int): Builder {
            params.mAnimationStyle = animationStyle
            return this
        }

        /**
         * 点击外部区域是否隐藏
         */
        fun setCanceledOnTouchOutside(cancel: Boolean): Builder {
            params.mCanceledOnTouchOutside = cancel
            return this
        }

        /**
         * 点击返回按钮是否隐藏
         */
        fun setCancelable(cancelable: Boolean): Builder {
            params.mCancelable = cancelable
            return this
        }

        // 创建Dialog
        fun build(): BaseDialog {
            mDialog = BaseDialog(context, params)
            convertView(mViewHolder, mDialog!!)
            return mDialog!!
        }

        // Dialog是否被创建
        fun isCreatedDialog(): Boolean {
            return mDialog != null
        }

        // Dialog是否显示
        fun isShowing(): Boolean {
            if (mDialog != null && mDialog!!.isShowing) {
                return true
            }
            return false
        }

        abstract fun convertView(viewHolder: ViewHolder, dialog: BaseDialog)

        // 显示Dialog
        open fun show() {
            if (mActivity == null || mActivity!!.isFinishing || mActivity!!.isDestroyed) {
                return
            }
            if (!isCreatedDialog()) {
                build()
            }
            if (isShowing()) {
                return
            }
            mDialog!!.show()
        }

        // 隐藏Dialog
        fun dismiss() {
            if (mActivity == null || mActivity!!.isFinishing || mActivity!!.isDestroyed) {
                return
            }
            if (mDialog == null) {
                return
            }
            mDialog!!.dismiss()
        }
    }
}