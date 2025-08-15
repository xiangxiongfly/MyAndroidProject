package com.example.androidui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.androidui.R
import com.example.core.utils.KeyboardUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyFullDialog : BottomSheetDialogFragment() {
    private lateinit var close: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MyBottomSheetDialogBgStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_full, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        close = view.findViewById(R.id.iv_close)
        close.setOnClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
//        val view: FrameLayout = dialog?.findViewById(R.id.design_bottom_sheet)!!
        //设置BottomSheetDialogFragment高度
//        view.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//        //设置弹出高度
//        val behavior = BottomSheetBehavior.from(view)
//        behavior.peekHeight = 3000
//        //展开
//        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun dismiss() {
        dialog?.currentFocus?.let { KeyboardUtils.hideSoftInput(it) }
        super.dismiss()
    }
}