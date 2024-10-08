package com.example.tools.clickarea

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.base.BaseActivity
import com.example.tools.R

/**
 * 扩大View点击区域的几种方式
 */
class ClickAreaActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_area)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_padding -> {
                startActivity(Intent(this, PaddingActivity::class.java))
            }
            R.id.btn_touch_delegate -> {
                startActivity(Intent(this, TouchDelegateActivity::class.java))
            }
            R.id.btn_location_on_screen -> {
                startActivity(Intent(this, LocationOnScreenActivity::class.java))
            }
        }
    }
}