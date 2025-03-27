package com.xiangxiongfly.androidtools.dialog.sample

import android.content.Context
import android.view.Gravity
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.dialog.BaseDialog
import com.xiangxiongfly.common.dialog.ViewHolder
import com.xiangxiongfly.common.exts.dp

class SettingDialog {

    class Builder(context: Context) : BaseDialog.Builder(context, R.layout.tools_dialog_setting) {
        override fun convertView(viewHolder: ViewHolder, dialog: BaseDialog) {
            setGravity(Gravity.BOTTOM)
            setAnimationStyle(R.style.DialogAnim)
            setHeight(300.dp)
        }
    }
}