package com.xiangxiongfly.core.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xiangxiongfly.core.action.HandlerAction
import com.xiangxiongfly.core.utils.LogUtils

open class BaseActivity : AppCompatActivity(), HandlerAction {
    protected lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        LogUtils.e("TAG", this.javaClass.simpleName.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        removeCallbacks()
    }
}
