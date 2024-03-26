package com.example.base.popupwindow

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import com.example.base.dialog.ViewHolder

class BasePopupWindow(private val params: PopupWindowParams, private val build: Builder) :
    PopupWindow() {

    override fun dismiss() {
        super.dismiss()
        if (params.mDimEnable) {
            build.setBackgroundDim(false)
        }
    }

    class PopupWindowParams {
        var mWidth: Int = ViewGroup.LayoutParams.WRAP_CONTENT // 宽度
        var mHeight: Int = ViewGroup.LayoutParams.WRAP_CONTENT // 高度
        var mFocusable: Boolean = false // 焦点
        var mBackground: Drawable = ColorDrawable() // 背景
        var mGravity: Int = Gravity.NO_GRAVITY // 位置
        var x: Int = 0 // x偏移
        var y: Int = 0 // y偏移
        var mDimEnable: Boolean = false // 背景是否暗淡

        @StyleRes
        var mAnimationStyle: Int = -1 // 隐藏监听
        var mOnDismissListener: OnDismissListener? = null // 隐藏监听
    }

    abstract class Builder(val context: Context, @LayoutRes layoutResId: Int) {
        private val mParams = PopupWindowParams()
        private val mContentView: View by lazy {
            LayoutInflater.from(context).inflate(layoutResId, null)
        }
        private val mViewHolder: ViewHolder by lazy {
            ViewHolder.create(mContentView)
        }
        private var mPopupWindow: BasePopupWindow? = null

        /**
         * 设置宽度
         */
        fun setWidth(width: Int): Builder {
            mParams.mWidth = width
            return this
        }

        /**
         * 设置高度
         */
        fun setHeight(height: Int): Builder {
            mParams.mHeight = height
            return this
        }

        /**
         * 设置焦点
         */
        fun setFocusable(focusable: Boolean): Builder {
            mParams.mFocusable = focusable
            return this
        }

        /**
         * 设置背景
         */
        fun setBackgroundDrawable(background: Drawable): Builder {
            mParams.mBackground = background
            return this
        }

        /**
         * 设置位置
         */
        fun setGravity(gravity: Int): Builder {
            mParams.mGravity = gravity
            return this
        }

        /**
         * 设置x偏移
         */
        fun setXOffset(x: Int): Builder {
            mParams.x = x
            return this
        }

        /**
         * 设置y便宜
         */
        fun setYOffset(y: Int): Builder {
            mParams.y = y
            return this
        }

        /**
         * 获取Activity
         */
        protected fun getActivity(context: Context): Activity? {
            if (context is Activity) {
                return context
            }
            return null
        }

        /**
         * 设置背景是否暗淡
         */
        fun setDimEnable(enable: Boolean): Builder {
            mParams.mDimEnable = enable
            return this
        }

        /**
         * 设置背景暗淡
         */
        fun setBackgroundDim(isDim: Boolean) {
            if (isDim) {
                setBackgroundAlpha(0.6F)
            } else {
                setBackgroundAlpha(1F)
            }
        }

        /**
         * 设置背景暗淡度
         */
        fun setBackgroundAlpha(alpha: Float) {
            val activity = getActivity(context)
            activity?.let { act ->
                act.window.let { w ->
                    val layoutParams = w.attributes
                    layoutParams.alpha = alpha
                    w.attributes = layoutParams
                }
            }
        }

        /**
         * 设置隐藏监听
         */
        fun setOnDismissListener(onDismissListener: OnDismissListener): Builder {
            mParams.mOnDismissListener = onDismissListener
            return this
        }

        /**
         * 设置动画
         */
        fun setAnimationStyle(@StyleRes animationStyle: Int): Builder {
            mParams.mAnimationStyle = animationStyle
            return this
        }

        /**
         * 获取PopupWindow
         */
        fun getPopupWindow(): PopupWindow {
            return mPopupWindow!!
        }

        /**
         * 创建PopupWindow
         */
        fun build(): Builder {
            mPopupWindow = BasePopupWindow(mParams, this)
            mPopupWindow!!.apply {
                contentView = mContentView
                width = mParams.mWidth
                height = mParams.mHeight
                isFocusable = mParams.mFocusable
                setBackgroundDrawable(mParams.mBackground)
                if (mParams.mAnimationStyle != -1) {
                    animationStyle = mParams.mAnimationStyle
                }
                if (mParams.mOnDismissListener != null) {
                    setOnDismissListener(mParams.mOnDismissListener)
                }
            }
            convertView(mViewHolder, mPopupWindow!!)
            return this
        }

        abstract fun convertView(viewHolder: ViewHolder, popupWindow: BasePopupWindow)

        /**
         * 判断PopupWindow是否被创建
         */
        fun isCreated(): Boolean {
            return mPopupWindow != null
        }

        /**
         * 判断PopupWindow是否显示
         */
        fun isShowing(): Boolean {
            return mPopupWindow!!.isShowing
        }

        private fun show(): Boolean {
            if (!isCreated()) {
                build()
            }
            if (isShowing()) {
                return false
            }
            if (mParams.mDimEnable) {
                setBackgroundDim(true)
            }
            return true
        }

        /**
         * 在指定视图的下方显示弹出窗口
         */
        fun showAsDropDown(anchor: View) {
            if (show()) {
                mPopupWindow!!.showAsDropDown(anchor, mParams.x, mParams.y, mParams.mGravity)
            }
        }

        /**
         * 在指定位置显示弹出窗口
         */
        fun showAtLocation(parent: View) {
            if (show()) {
                mPopupWindow!!.showAtLocation(parent, mParams.mGravity, mParams.x, mParams.y)
            }
        }
    }
}