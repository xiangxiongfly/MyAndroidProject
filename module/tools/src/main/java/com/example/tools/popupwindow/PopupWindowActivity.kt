package com.example.tools.popupwindow

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tools.R
import com.example.tools.popupwindow.sample.LikePop
import com.example.tools.popupwindow.sample.QQPop
import com.example.tools.popupwindow.sample.SharePop
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
        SharePop.Build(mContext)
            .show(btnShare)
    }

    fun showQQ(view: View) {
        QQPop.Builder(mContext)
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
        LikePop.Builder(mContext)
            .show(view)
    }
}