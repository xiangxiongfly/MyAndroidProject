package com.example.tools.dialog.sample

import android.content.Context
import android.view.Gravity
import android.view.View
import com.example.tools.R
import com.example.tools.dialog.base.BaseDialog
import com.example.tools.dialog.base.ViewHolder

class ShareDialog {

    class Builder(context: Context) : BaseDialog.Builder(context, R.layout.tools_dialog_share) {
        override fun convertView(viewHolder: ViewHolder, dialog: BaseDialog) {
            setGravity(Gravity.BOTTOM)
            setAnimationStyle(R.style.DialogAnim)
            viewHolder.setOnClickListener(R.id.tv_weixin_share, object : View.OnClickListener {
                override fun onClick(v: View?) {
                    dialog.dismiss()
                }
            })
            viewHolder.setOnClickListener(R.id.tv_qq_share, object : View.OnClickListener {
                override fun onClick(v: View?) {
                    dialog.dismiss()
                }
            })
            viewHolder.setOnClickListener(R.id.tv_weibo_share, object : View.OnClickListener {
                override fun onClick(v: View?) {
                    dialog.dismiss()
                }
            })
        }
    }
}