package com.example.tools.dialog.sample

import android.content.Context
import com.example.tools.R
import com.example.tools.dialog.base.BaseDialog
import com.example.tools.dialog.base.ViewHolder
import com.xiangxiongfly.common.action.HandlerAction
import com.xiangxiongfly.common.action.HandlerAction.Companion.HANDLER

class TipDialog : HandlerAction {
    class Build(context: Context) : BaseDialog.Builder(context, R.layout.tools_dialog_tip) {
        override fun convertView(viewHolder: ViewHolder, dialog: BaseDialog) {
        }

        fun show(delayMillis: Long) {
            super.show()
            HANDLER.postDelayed({ dismiss() }, delayMillis)
        }
    }
}