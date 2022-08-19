package com.xiangxiongfly.common.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.xiangxiongfly.common.action.HandlerAction

const val KEY_TITLE = "title"

open class BaseActivity : AppCompatActivity(), HandlerAction {
    protected lateinit var mContext: Context
    protected lateinit var mActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TAG", this.javaClass.simpleName.toString())
        mContext = this
        mActivity = this
        if (intent.hasExtra(KEY_TITLE)) {
            val title = intent.getStringExtra(KEY_TITLE)
            setTitle(title)
        }
    }
}
