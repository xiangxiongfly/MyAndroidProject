package com.xiangxiongfly.androidtools.popupwindow.sample

import android.content.Context
import android.view.View
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.exts.dp
import com.xiangxiongfly.common.popupwindow.BasePopupWindow
import com.xiangxiongfly.common.popupwindow.ViewHolder
import com.xiangxiongfly.common.utils.ToastUtils

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
            setYOffset(location[1] - contentView.measuredHeight - 5.dp)
            showAtLocation(view)
        }
    }
}