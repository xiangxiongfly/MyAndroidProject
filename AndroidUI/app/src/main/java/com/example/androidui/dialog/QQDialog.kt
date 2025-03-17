package com.example.androidui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.androidui.R

class QQDialog(context: Context) : Dialog(context, R.style.DialogStyle_Animation),
    View.OnClickListener {

    private lateinit var video: TextView
    private lateinit var photo: TextView
    private lateinit var cancel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_qq)
        window?.apply {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ) //设置宽高
            setGravity(Gravity.BOTTOM) //设置显示位置
        }
        setCanceledOnTouchOutside(true) //点击外部区域是否取消
        setCancelable(true) //按返回键是否取消
        initViews()
    }

    private fun initViews() {
        video = findViewById(R.id.video)
        photo = findViewById(R.id.photo)
        cancel = findViewById(R.id.cancel)
        video.setOnClickListener(this)
        photo.setOnClickListener(this)
        cancel.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.video -> {
                dismiss()
                mOnDialogClickListener?.onVideoClick()
            }
            R.id.photo -> {
                dismiss()
                mOnDialogClickListener?.onPhotoClick()
            }
            R.id.cancel -> dismiss()
        }
    }

    interface OnDialogClickListener {
        fun onVideoClick()
        fun onPhotoClick()
    }

    private var mOnDialogClickListener: OnDialogClickListener? = null

    fun setOnDialogClickListener(onDialogClickListener: OnDialogClickListener) {
        mOnDialogClickListener = onDialogClickListener
    }
}