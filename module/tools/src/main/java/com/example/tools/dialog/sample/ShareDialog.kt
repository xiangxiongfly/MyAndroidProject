package com.example.tools.dialog.sample

import android.content.Context
import android.view.Gravity
import android.view.View
import com.example.base.dialog.BaseDialog
import com.example.base.dialog.ViewHolder
import com.example.tools.R

class ShareDialog {

    class Builder(context: Context) : BaseDialog.Builder(context, R.layout.tools_dialog_share) {
        init {
            setGravity(Gravity.BOTTOM)
            setAnimationStyle(R.style.DialogAnim)
        }

        override fun convertView(viewHolder: ViewHolder, dialog: BaseDialog) {
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