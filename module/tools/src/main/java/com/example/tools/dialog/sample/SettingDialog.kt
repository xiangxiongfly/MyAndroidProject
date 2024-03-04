package com.example.tools.dialog.sample

import android.content.Context
import android.view.Gravity
import com.example.tools.R
import com.example.tools.dialog.base.BaseDialog
import com.example.tools.dialog.base.ViewHolder
import com.xiangxiongfly.common.utils.dp2px

class SettingDialog {

    class Builder(context: Context) : BaseDialog.Builder(context, R.layout.tools_dialog_setting) {
        override fun convertView(viewHolder: ViewHolder, dialog: BaseDialog) {
            setGravity(Gravity.BOTTOM)
            setAnimationStyle(R.style.DialogAnim)
            setHeight(dp2px(300))
        }
    }
}