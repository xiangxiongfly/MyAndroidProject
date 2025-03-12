package com.example.base.utils

import android.widget.Toast
import com.example.base.BaseApplication

object ToastUtils {
    private var sToast: Toast? = null

    @JvmStatic
    fun show(text: String) {
        if (sToast == null) {
            sToast = Toast.makeText(
                BaseApplication.getInstance().applicationContext,
                null,
                Toast.LENGTH_SHORT
            )
        }
        sToast!!.setText(text)
        sToast!!.show()
    }

}

fun showToast(text: String) {
    ToastUtils.show(text)
}