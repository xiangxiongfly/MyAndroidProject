package com.example.tools.popupwindow.sample

import android.content.Context
import android.view.View
import com.example.base.dialog.ViewHolder
import com.example.base.popupwindow.BasePopupWindow
import com.example.base.utils.ToastUtils
import com.example.base.utils.dp2px
import com.example.tools.R

class LikePop {

    class Builder(context: Context) : BasePopupWindow.Builder(context, R.layout.pop_like) {
        init {
            setFocusable(true)
        }

        override fun convertView(viewHolder: ViewHolder, popupWindow: BasePopupWindow) {
            viewHolder.setOnClickListener(R.id.like) {
                popupWindow.dismiss()
                ToastUtils.show("喜欢")
            }
            viewHolder.setOnClickListener(R.id.collection) {
                popupWindow.dismiss()
                ToastUtils.show("收藏")
            }
        }

        fun show(view: View) {
            build()

            val popupWindow = getPopupWindow()

            val contentView = popupWindow.contentView
            contentView.measure(
                View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED
            )
            val diff = (contentView.measuredWidth - view.measuredWidth) / 2

            val location = IntArray(2)
            view.getLocationOnScreen(location)
            setXOffset(location[0] - diff)
            setYOffset(location[1] - contentView.measuredHeight - dp2px(5))
            showAtLocation(view)
        }
    }
}