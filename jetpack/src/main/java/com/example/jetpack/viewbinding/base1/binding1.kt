package com.example.jetpack.viewbinding.base1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.BaseFragment


/**
 * 基类封装，不使用反射
 */

abstract class BindingActivity<VB : ViewBinding> : BaseActivity() {
    private lateinit var _viewBinding: VB
    protected val mViewBinding get() = _viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = getViewBinding()
        setContentView(_viewBinding.root)
    }

    abstract fun getViewBinding(): VB
}

abstract class BindingFragment<VB : ViewBinding> : BaseFragment() {
    private var _viewBinding: VB? = null
    protected val mViewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = getViewBinding(inflater, container)
        return mViewBinding.root
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}
