package com.example.home.bottom_sheet

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.home.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.xiangxiongfly.common.base.BaseActivity

class BottomSheetActivity : BaseActivity() {
    private lateinit var btnBottomSheet: Button
    private lateinit var llBottom: LinearLayout
    private lateinit var tvTitle: TextView
    private lateinit var btnBottomSheetDialog: Button
    private lateinit var btnBottomSheetDialogFragment: Button
    private lateinit var btnFullDialog: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)
        initView()
        initBottomSheet()
        initBottomSheetDialog()
        initBottomSheetDialogFragment()
        initFullDialog()
    }

    private fun initView() {
        btnBottomSheet = findViewById(R.id.btn_bottom_sheet)
        llBottom = findViewById(R.id.ll_bottom)
        tvTitle = findViewById(R.id.tv_title)
        btnBottomSheetDialog = findViewById(R.id.btn_bottom_sheet_dialog)
        btnBottomSheetDialogFragment = findViewById(R.id.btn_bottom_sheet_dialog_fragment)
        btnFullDialog = findViewById(R.id.btn_full_dialog)
    }

    /**
     * 拖拽的BottomSheet
     */
    private fun initBottomSheet() {
        val behavior = BottomSheetBehavior.from(llBottom)
        btnBottomSheet.setOnClickListener {
            if (behavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            } else if (behavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    tvTitle.text = "下滑收起"
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    tvTitle.text = "上拉展开"
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })
    }

    /**
     * BottomSheetDialog
     */
    private fun initBottomSheetDialog() {
        btnBottomSheetDialog.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(mContext, R.style.MyBottomSheetDialogStyle)
            bottomSheetDialog.dismissWithAnimation
            bottomSheetDialog.setContentView(R.layout.dialog_bottom_sheet)
            val cancel: TextView = bottomSheetDialog.findViewById<TextView>(R.id.cancel)!!
            cancel.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.show()
        }
    }

    /**
     * BottomSheetDialogFragment
     */
    private fun initBottomSheetDialogFragment() {
        btnBottomSheetDialogFragment.setOnClickListener {
            MyBottomSheetDialog().show(supportFragmentManager, "MyBottomSheetDialog")
        }
    }

    /**
     * 全屏背景无阴影的BottomSheetDialogFragment
     */
    private fun initFullDialog() {
        btnFullDialog.setOnClickListener {
            MyFullDialog().show(supportFragmentManager, "MyFullDialog")
        }
    }
}