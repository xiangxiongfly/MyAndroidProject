package com.example.tools.popupwindow.sample

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.example.tools.R
import com.example.tools.dialog.base.ViewHolder
import com.example.tools.popupwindow.base.BasePopupWindow

class QQPop {

    class Builder(context: Context) : BasePopupWindow.Builder(context, R.layout.pop_qq) {
        private var mOnClickListener: OnClickListener? = null

        init {
            setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
            setFocusable(true)
            setGravity(Gravity.BOTTOM)
            setDimEnable(true)
            setAnimationStyle(R.style.DialogAnim)
        }

        override fun convertView(viewHolder: ViewHolder, popupWindow: BasePopupWindow) {
            viewHolder.setOnClickListener(R.id.video) {
                popupWindow.dismiss()
                mOnClickListener?.onClickVideo()
            }
            viewHolder.setOnClickListener(R.id.photo) {
                popupWindow.dismiss()
                mOnClickListener?.onClickPhoto()
            }
            viewHolder.setOnClickListener(R.id.cancel) { popupWindow.dismiss() }
        }

        fun setOnClickListener(listener: OnClickListener): Builder {
            mOnClickListener = listener
            return this
        }

        fun show(root: View) {
            showAtLocation(root)
        }
    }

    interface OnClickListener {
        fun onClickVideo()
        fun onClickPhoto()
    }
}