package com.example.tools.dialog.sample

import android.content.Context
import android.view.Gravity
import com.example.base.dialog.BaseDialog
import com.example.base.dialog.ViewHolder
import com.example.base.utils.dp2px
import com.example.tools.R

class SettingDialog {

    class Builder(context: Context) : BaseDialog.Builder(context, R.layout.tools_dialog_setting) {
        override fun convertView(viewHolder: ViewHolder, dialog: BaseDialog) {
            setGravity(Gravity.BOTTOM)
            setAnimationStyle(R.style.DialogAnim)
            setHeight(dp2px(300))
        }
    }
}