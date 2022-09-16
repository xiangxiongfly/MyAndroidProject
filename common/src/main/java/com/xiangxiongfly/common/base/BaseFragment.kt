package com.xiangxiongfly.common.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.xiangxiongfly.common.action.HandlerAction

open class BaseFragment : Fragment(), HandlerAction {
    protected lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDestroy() {
        super.onDestroy()
        removeCallbacks()
    }
}