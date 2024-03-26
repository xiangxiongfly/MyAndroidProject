package com.example.tools.dialog.sample

import android.content.Context
import android.view.View
import com.example.base.dialog.BaseDialog
import com.example.base.dialog.ViewHolder
import com.example.base.utils.ScreenUtils
import com.example.base.utils.dp2px
import com.example.tools.R

class ConfirmDialog {

    class Builder(context: Context) : BaseDialog.Builder(context, R.layout.tools_dialog_confirm) {
        private var mTitle: String? = null
        private var mContent: String? = null
        private var mOnClickListener: OnClickListener? = null

        override fun convertView(viewHolder: ViewHolder, dialog: BaseDialog) {
            setWidth(ScreenUtils.getScreenWidth(context))
            setHorizontalGap(dp2px(50))
            setCancelable(false)
            setCanceledOnTouchOutside(false)

            viewHolder.setText(R.id.title, mTitle!!)
            viewHolder.setText(R.id.content, mContent!!)
            viewHolder.setOnClickListener(R.id.cancel, object : View.OnClickListener {
                override fun onClick(v: View?) {
                    mOnClickListener?.onCancel()
                    dialog.dismiss()
                }
            })
            viewHolder.setOnClickListener(R.id.confirm) {
                mOnClickListener?.onConfirm()
                dialog.dismiss()
            }
        }

        fun setTitle(title: String): Builder {
            mTitle = title
            return this
        }

        fun setContent(content: String): Builder {
            mContent = content
            return this
        }

        fun setOnClickListener(listener: OnClickListener): Builder {
            mOnClickListener = listener
            return this
        }
    }

    interface OnClickListener {
        fun onConfirm()
        fun onCancel()
    }
}