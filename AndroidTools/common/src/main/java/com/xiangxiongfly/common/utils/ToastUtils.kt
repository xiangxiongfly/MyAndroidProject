package com.xiangxiongfly.common.utils

import android.widget.Toast
import com.xiangxiongfly.common.base.BaseApplication

object ToastUtils {
    private val toast: Toast by lazy {
        Toast.makeText(BaseApplication.getInstance().applicationContext, null, Toast.LENGTH_SHORT)
    }

    @JvmStatic
    fun show(text: String) {
        toast.let {
            it.setText(text)
            it.show()
        }
    }
}