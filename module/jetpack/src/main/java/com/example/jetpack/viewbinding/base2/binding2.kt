package com.example.jetpack.viewbinding.base2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.base.BaseActivity
import com.example.base.BaseFragment
import java.lang.reflect.ParameterizedType

/**
 * 基类封装，使用反射
 */
abstract class BindingActivity<VB : ViewBinding> : BaseActivity() {
    private lateinit var _viewBinding: VB
    protected val mViewBinding get() = _viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val clz = type.actualTypeArguments[0] as Class<*>
            val method = clz.getMethod("inflate", LayoutInflater::class.java)
            _viewBinding = method.invoke(null, layoutInflater) as VB
            setContentView(_viewBinding.root)
        }
    }
}

abstract class BindingFragment<VB : ViewBinding> : BaseFragment() {
    private var _viewBinding: VB? = null
    protected val mViewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val clz = type.actualTypeArguments[0] as Class<*>
            val method = clz.getMethod(
                "inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java
            )
            _viewBinding = method.invoke(null, inflater, container, false) as VB
        }
        return mViewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}