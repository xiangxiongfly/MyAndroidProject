package com.example.androidui.popupwindow

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import com.example.androidui.R
import com.example.core.base.BaseActivity
import com.example.core.utils.dp

class PopupWindowActivity : BaseActivity() {
    private lateinit var root: View
    private lateinit var btnShare: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_window)
        initViews()
    }

    private fun initViews() {
        root = findViewById(R.id.root)
        btnShare = findViewById(R.id.btn_share)
    }

    fun showShare(view: View) {
        val view = LayoutInflater.from(context).inflate(R.layout.pop_share, null)
        val tvQQ = view.findViewById<TextView>(R.id.tv_qq)
        val tvWeixin = view.findViewById<TextView>(R.id.tv_weixin)
        val tvSina = view.findViewById<TextView>(R.id.tv_sina)
        val sharePop = PopupWindow(view, 100.dp, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        sharePop.apply {
            setBackgroundDrawable(ColorDrawable())
            setOnDismissListener { setDimEnable(false) }
        }
        setDimEnable(true)
        sharePop.showAsDropDown(btnShare, -30, 0, Gravity.RIGHT)
        tvQQ.setOnClickListener { sharePop.dismiss() }
        tvWeixin.setOnClickListener { sharePop.dismiss() }
        tvSina.setOnClickListener { sharePop.dismiss() }
    }

    fun showQQ(view: View) {
        val view = LayoutInflater.from(context).inflate(R.layout.pop_qq, null)
        val cancel = view.findViewById<TextView>(R.id.cancel)
        val qqPop = PopupWindow(
            view,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
        qqPop.apply {
            setBackgroundDrawable(ColorDrawable())
            animationStyle = R.style.DialogAnim
            setOnDismissListener { setDimEnable(false) }
        }
        setDimEnable(true)
        qqPop.showAtLocation(root, Gravity.BOTTOM, 0, 0)
        cancel.setOnClickListener { qqPop.dismiss() }
    }

    /**
     * 设置背景是否昏暗
     */
    private fun setDimEnable(enable: Boolean) {
        if (enable) {
            setBackgroundAlpha(0.6F)
        } else {
            setBackgroundAlpha(1F)
        }
    }

    /**
     * 设置背景透明度
     */
    private fun setBackgroundAlpha(alpha: Float) {
        window.let { w ->
            val layoutParams = w.attributes
            layoutParams.alpha = alpha
            w.attributes = layoutParams
        }
    }
}