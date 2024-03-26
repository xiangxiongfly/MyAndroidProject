package com.example.tools.dialog

import android.os.Bundle
import android.view.View
import com.example.base.BaseActivity
import com.example.base.utils.ToastUtils
import com.example.tools.R
import com.example.tools.dialog.sample.*

class DialogActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tools_activity_dialog)
    }

    fun showConfirm1(view: View) {
        ConfirmDialog.Builder(mContext)
            .setTitle("标题")
            .setContent("这是一些内容")
            .show()
    }

    fun showConfirm2(view: View) {
        val dialog = ConfirmDialog.Builder(mContext)
            .setTitle("提示")
            .setContent("您已支付成功")
            .setOnClickListener(object : ConfirmDialog.OnClickListener {
                override fun onConfirm() {
                    ToastUtils.show("确定")
                }

                override fun onCancel() {
                    ToastUtils.show("取消")
                }
            })
            .build()
        dialog.show()
    }

    fun showShare(view: View) {
        ShareDialog.Builder(mContext)
            .show()
    }

    fun showSetting(view: View) {
        SettingDialog.Builder(mContext)
            .show()
    }

    fun showComment(view: View) {
        CommentDialog.Builder(mContext)
            .show()
    }

    fun showTip(view: View) {
        TipsDialog.Build(mContext)
            .show(2000L)
    }
}