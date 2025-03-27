package com.xiangxiongfly.common.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.xiangxiongfly.common.action.HandlerAction

open class BaseActivity : AppCompatActivity(), HandlerAction {
    protected lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        Log.e("TAG", this.javaClass.simpleName.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        removeCallbacks()
    }
}
