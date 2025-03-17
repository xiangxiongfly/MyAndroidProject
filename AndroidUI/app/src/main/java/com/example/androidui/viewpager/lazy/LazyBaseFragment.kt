package com.example.androidui.viewpager.lazy

import android.os.Bundle
import com.example.core.base.BaseFragment

/**
 * setUserVisibleHint方法判断Fragment是否对用户可见
 * onActivityCreated方法判断View是否被创建
 */
abstract class LazyBaseFragment : BaseFragment() {
    //View是否创建
    private var isViewCreated = false

    //Fragment是否可见
    private var isVisibleToUser = false

    //是否加载数据
    private var isLoadData = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewCreated = true
        prepareFetchData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        if (isVisibleToUser) {
            prepareFetchData()
        }
    }

    protected fun prepareFetchData() {
        prepareFetchData(false)
    }

    protected fun prepareFetchData(forceUpdate: Boolean) {
        if (isVisibleToUser && isViewCreated && (!isLoadData || forceUpdate)) {
            loadData()
            isLoadData = true
        }
    }

    abstract fun loadData()
}