package com.xiangxiongfly.common.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xiangxiongfly.common.action.HandlerAction
import com.xiangxiongfly.common.utils.LogUtils

const val KEY_TITLE = "title"

open class BaseActivity : AppCompatActivity(), HandlerAction {
    protected lateinit var mContext: Context
    protected lateinit var mActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.e("TAG", this.javaClass.simpleName.toString())
        mContext = this
        mActivity = this
    }

    override fun onDestroy() {
        super.onDestroy()
        removeCallbacks()
    }
}
