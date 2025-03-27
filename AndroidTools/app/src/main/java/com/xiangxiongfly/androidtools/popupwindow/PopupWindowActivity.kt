package com.xiangxiongfly.androidtools.popupwindow

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.androidtools.popupwindow.sample.LikePop
import com.xiangxiongfly.androidtools.popupwindow.sample.QQPop
import com.xiangxiongfly.androidtools.popupwindow.sample.SharePop
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.utils.ToastUtils

class PopupWindowActivity : BaseActivity() {
    private lateinit var root: View
    private lateinit var btnShare: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tools_activity_popup_window)
        initViews()
    }

    private fun initViews() {
        root = findViewById(R.id.root)
        btnShare = findViewById(R.id.btn_share)
    }

    fun showShare(view: View) {
        SharePop.Build(context)
            .show(btnShare)
    }

    fun showQQ(view: View) {
        QQPop.Builder(context)
            .setOnClickListener(object : QQPop.OnClickListener {
                override fun onClickVideo() {
                    ToastUtils.show("视频")
                }

                override fun onClickPhoto() {
                    ToastUtils.show("相册")
                }
            })
            .show(root)
    }

    fun showLike(view: View) {
        LikePop.Builder(context)
            .show(view)
    }
}