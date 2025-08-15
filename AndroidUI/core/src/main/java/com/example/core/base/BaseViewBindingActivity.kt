package com.example.core.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class BaseViewBindingActivity<VB : ViewBinding> : BaseActivity() {
    private var _binding: VB? = null
    protected val binding get() = _binding ?: throw IllegalStateException("ViewBinding没有初始化")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type: Type? = javaClass.genericSuperclass //获取父类的泛型类型
        if (type != null && type is ParameterizedType) {
            val clz = type.actualTypeArguments[0] as Class<*>
            val method = clz.getMethod("inflate", LayoutInflater::class.java)
            _binding = method.invoke(null, layoutInflater) as VB
            setContentView(binding.root)
        }
        initViews()
        initData()
    }

    abstract fun initViews()

    abstract fun initData()
}