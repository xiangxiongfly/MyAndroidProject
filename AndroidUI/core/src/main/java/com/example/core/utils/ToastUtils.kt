package com.example.core.utils

import android.widget.Toast
import com.example.core.base.BaseApplication

object ToastUtils {
    private var sToast: Toast? = null

    @JvmStatic
    fun show(text: String) {
        if (sToast == null) {
            sToast = Toast.makeText(
                BaseApplication.getInstance(),
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