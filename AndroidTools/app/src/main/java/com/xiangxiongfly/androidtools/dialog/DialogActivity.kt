package com.xiangxiongfly.androidtools.dialog

import android.os.Bundle
import android.view.View
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.androidtools.dialog.sample.CommentDialog
import com.xiangxiongfly.androidtools.dialog.sample.ConfirmDialog
import com.xiangxiongfly.androidtools.dialog.sample.SettingDialog
import com.xiangxiongfly.androidtools.dialog.sample.ShareDialog
import com.xiangxiongfly.androidtools.dialog.sample.TipsDialog
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.utils.ToastUtils

class DialogActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tools_activity_dialog)
    }

    fun showConfirm1(view: View) {
        ConfirmDialog.Builder(context)
            .setTitle("标题")
            .setContent("这是一些内容")
            .show()
    }

    fun showConfirm2(view: View) {
        val dialog = ConfirmDialog.Builder(context)
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
        ShareDialog.Builder(context)
            .show()
    }

    fun showSetting(view: View) {
        SettingDialog.Builder(context)
            .show()
    }

    fun showComment(view: View) {
        CommentDialog.Builder(context)
            .show()
    }

    fun showTip(view: View) {
        TipsDialog.Build(context)
            .show(2000L)
    }
}