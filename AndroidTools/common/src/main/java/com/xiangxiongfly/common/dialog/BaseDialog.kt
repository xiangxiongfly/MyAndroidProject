package com.xiangxiongfly.common.dialog

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.FloatRange
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDialog
import com.xiangxiongfly.common.R
import com.xiangxiongfly.common.dialog.DialogParams.Companion.NONE_ANIM_STYLE

/**
 * BaseDialog
 */
class BaseDialog(context: Context, private val params: DialogParams) :
    AppCompatDialog(context, R.style.BaseDialogStyle) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(params.view)
        if (params.width == FrameLayout.LayoutParams.WRAP_CONTENT) {
            params.width = params.view.layoutParams.width
        }
        if (params.height == FrameLayout.LayoutParams.WRAP_CONTENT) {
            params.height = params.view.layoutParams.height
        }
        window?.let { w ->
            val layoutParams = w.attributes
            layoutParams.apply {
                width = params.width
                if (width > 0) {
                    width -= (params.gap * 2)
                }
                height = params.height
                gravity = params.gravity
                if (params.animationStyle != NONE_ANIM_STYLE) {
                    windowAnimations = params.animationStyle
                }
                if (params.dimEnabled) {
                    w.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                    dimAmount = params.dimAmount
                } else {
                    w.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                }
            }
            w.attributes = layoutParams
        }
        setCanceledOnTouchOutside(params.canceledOnTouchOutside) //点击外部区域是否取消
        setCancelable(params.cancelable) //按返回键是否取消
        params.onShowListener?.let {
            setOnShowListener(it)
        }
        params.onDismissListener?.let {
            setOnDismissListener(it)
        }
    }

    abstract class Builder(val context: Context, @LayoutRes layoutId: Int) {
        private var dialogParams: DialogParams = DialogParams()
        private var dialog: BaseDialog? = null
        private var activity: Activity? = null
        private val dialogView: View by lazy {
            LayoutInflater.from(context).inflate(layoutId, FrameLayout(context), false)
        }
        private val viewHolder: ViewHolder by lazy {
            ViewHolder.create(dialogView)
        }

        init {
            dialogParams.view = dialogView
            activity = getActivity(context)
        }

        protected fun getActivity(context: Context): Activity? {
            if (context is Activity) {
                return context
            }
            return null
        }

        /**
         * 是否显示背景暗淡度
         */
        protected fun setDimEnable(enable: Boolean): Builder {
            dialogParams.dimEnabled = enable
            return this
        }

        /**
         * 设置背景暗淡度
         */
        protected fun setDimAmount(@FloatRange(from = 0.0, to = 1.0) dimAmount: Float): Builder {
            dialogParams.dimAmount = dimAmount
            return this
        }

        /**
         * 设置显示位置
         */
        protected fun setGravity(gravity: Int): Builder {
            dialogParams.gravity = gravity
            return this
        }

        /**
         * 设置宽高
         */
        protected fun setWidth(width: Int): Builder {
            dialogParams.width = width
            return this
        }

        protected fun setHeight(height: Int): Builder {
            dialogParams.height = height
            return this
        }

        /**
         * 设置左右空白
         */
        protected fun setHorizontalGap(gap: Int): Builder {
            dialogParams.gap = gap
            return this
        }

        /**
         * 动画
         */
        protected fun setAnimationStyle(@StyleRes animationStyle: Int): Builder {
            dialogParams.animationStyle = animationStyle
            return this
        }

        /**
         * 点击外部区域是否隐藏
         */
        protected fun setCanceledOnTouchOutside(cancel: Boolean): Builder {
            dialogParams.canceledOnTouchOutside = cancel
            return this
        }

        /**
         * 点击返回按钮是否隐藏
         */
        protected fun setCancelable(cancelable: Boolean): Builder {
            dialogParams.cancelable = cancelable
            return this
        }

        /**
         * 设置显示监听
         */
        protected fun setOnShowListener(onShowListener: DialogInterface.OnShowListener): Builder {
            dialogParams.onShowListener = onShowListener
            return this
        }

        /**
         * 设置隐藏监听
         */
        protected fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener): Builder {
            dialogParams.onDismissListener = onDismissListener
            return this
        }

        // 创建Dialog
        fun build(): BaseDialog {
            dialog = BaseDialog(context, dialogParams)
            convertView(viewHolder, dialog!!)
            return dialog!!
        }

        // Dialog是否被创建
        private fun isCreated(): Boolean {
            return dialog != null
        }

        // Dialog是否显示
        private fun isShowing(): Boolean {
            if (dialog != null && dialog!!.isShowing) {
                return true
            }
            return false
        }

        abstract fun convertView(viewHolder: ViewHolder, dialog: BaseDialog)

        // 显示Dialog
        open fun show() {
            if (activity == null || activity!!.isFinishing || activity!!.isDestroyed) {
                return
            }
            if (!isCreated()) {
                build()
            }
            if (isShowing()) {
                return
            }
            dialog!!.show()
        }

        // 隐藏Dialog
        fun dismiss() {
            if (activity == null || activity!!.isFinishing || activity!!.isDestroyed) {
                return
            }
            if (dialog == null) {
                return
            }
            dialog!!.dismiss()
        }
    }
}