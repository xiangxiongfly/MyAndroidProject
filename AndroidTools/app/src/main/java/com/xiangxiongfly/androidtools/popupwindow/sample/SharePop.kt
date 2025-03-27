package com.xiangxiongfly.androidtools.popupwindow.sample

import android.content.Context
import android.view.Gravity
import android.view.View
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.exts.dp
import com.xiangxiongfly.common.popupwindow.BasePopupWindow
import com.xiangxiongfly.common.popupwindow.ViewHolder
import com.xiangxiongfly.common.utils.ToastUtils

class SharePop {

    class Build(context: Context) : BasePopupWindow.Builder(context, R.layout.pop_share) {
        init {
            setWidth(100.dp)
            setFocusable(true)
            setXOffset(-30)
            setGravity(Gravity.RIGHT)
            setDimEnable(true)
        }

        override fun convertView(viewHolder: ViewHolder, popupWindow: BasePopupWindow) {
            viewHolder.setOnClickListener(R.id.tv_qq) {
                popupWindow.dismiss()
                ToastUtils.show("QQ")
            }
            viewHolder.setOnClickListener(R.id.tv_weixin) {
                popupWindow.dismiss()
                ToastUtils.show("微信")
            }
            viewHolder.setOnClickListener(R.id.tv_sina) {
                popupWindow.dismiss()
                ToastUtils.show("新浪")
            }
        }

        fun show(view: View) {
            showAsDropDown(view)
        }
    }
}