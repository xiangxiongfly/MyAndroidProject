package com.example.jetpack.lifecycle.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.base.BaseActivity
import com.example.jetpack.R

class LifecycleDialogActivity : BaseActivity() {
    private lateinit var dialog: LifecycleDialog

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LifecycleDialogActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_dialog)

        dialog = LifecycleDialog(mContext)
        dialog.show()
        postDelayed({
            finish()
        }, 2000L)
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        dialog.dismiss()
//    }
}