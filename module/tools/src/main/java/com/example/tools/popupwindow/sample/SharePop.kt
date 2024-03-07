package com.example.tools.popupwindow.sample

import android.content.Context
import android.view.Gravity
import android.view.View
import com.example.tools.R
import com.example.tools.dialog.base.ViewHolder
import com.example.tools.popupwindow.base.BasePopupWindow
import com.xiangxiongfly.common.utils.ToastUtils
import com.xiangxiongfly.common.utils.dp2px

class SharePop {

    class Build(context: Context) : BasePopupWindow.Builder(context, R.layout.pop_share) {
        init {
            setWidth(dp2px(100))
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