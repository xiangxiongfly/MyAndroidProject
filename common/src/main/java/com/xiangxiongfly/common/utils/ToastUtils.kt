package com.xiangxiongfly.common.utils

import android.widget.Toast
import com.xiangxiongfly.common.base.BaseApplication

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