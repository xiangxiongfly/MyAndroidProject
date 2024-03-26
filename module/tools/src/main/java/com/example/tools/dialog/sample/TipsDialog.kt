package com.example.tools.dialog.sample

import android.content.Context
import com.example.base.action.HandlerAction
import com.example.base.action.HandlerAction.Companion.HANDLER
import com.example.base.dialog.BaseDialog
import com.example.base.dialog.ViewHolder
import com.example.tools.R

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