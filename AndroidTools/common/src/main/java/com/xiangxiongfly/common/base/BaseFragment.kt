package com.xiangxiongfly.common.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.xiangxiongfly.common.action.HandlerAction

open class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId),
    HandlerAction {
    protected lateinit var mContext: Context

    constructor() : this(0)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDestroy() {
        super.onDestroy()
        removeCallbacks()
    }
}