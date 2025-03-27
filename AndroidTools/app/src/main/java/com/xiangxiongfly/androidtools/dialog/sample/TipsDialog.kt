package com.xiangxiongfly.androidtools.dialog.sample

import android.content.Context
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.action.HandlerAction
import com.xiangxiongfly.common.action.HandlerAction.Companion.HANDLER
import com.xiangxiongfly.common.dialog.BaseDialog
import com.xiangxiongfly.common.dialog.ViewHolder

class TipsDialog : HandlerAction {
    class Build(context: Context) : BaseDialog.Builder(context, R.layout.tools_dialog_tips) {
        init {
            setCanceledOnTouchOutside(false)
            setCancelable(false)
        }

        override fun convertView(viewHolder: ViewHolder, dialog: BaseDialog) {
        }

        fun show(delayMillis: Long) {
            super.show()
            HANDLER.postDelayed({ dismiss() }, delayMillis)
        }
    }
}