package com.example.widgets.statelayout

import android.view.View
import androidx.annotation.LayoutRes

/**
 * StateLayout全局配置
 */
object StateConfig {

    @LayoutRes
    @JvmStatic
    var emptyLayoutId = View.NO_ID

    @LayoutRes
    @JvmStatic
    var errorLayoutId = View.NO_ID

    @LayoutRes
    @JvmStatic
    var loadingLayoutId = View.NO_ID

    @LayoutRes
    @JvmStatic
    var retryIds: IntArray? = null

    private var mOnStateChangeListener: OnStateChangeListener? = null

    fun setOnStateChangeListener(listener: OnStateChangeListener) {
        mOnStateChangeListener = listener
    }

    fun getOnStateChangeListener(): OnStateChangeListener? {
        return mOnStateChangeListener
    }
}