package com.example.tools.dialog.sample

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.widget.EditText
import android.widget.TextView
import com.example.base.dialog.BaseDialog
import com.example.base.dialog.ViewHolder
import com.example.base.utils.ToastUtils
import com.example.tools.R

class CommentDialog {

    class Builder(context: Context) : BaseDialog.Builder(context, R.layout.tools_dialog_comment) {
        override fun convertView(viewHolder: ViewHolder, dialog: BaseDialog) {
            setGravity(Gravity.BOTTOM)
            setAnimationStyle(R.style.DialogAnim)
            val tvCommit = viewHolder.getView<TextView>(R.id.tv_commit)
            val etComment = viewHolder.getView<EditText>(R.id.et_comment)
            viewHolder.setOnClickListener(R.id.tv_commit) {
                val comment: String = etComment.text.toString().trim()
                if (TextUtils.isEmpty(comment)) {
                    ToastUtils.show("请输入评论")
                } else {
                    dialog.dismiss()
                    ToastUtils.show(comment)
                }
            }
        }
    }
}