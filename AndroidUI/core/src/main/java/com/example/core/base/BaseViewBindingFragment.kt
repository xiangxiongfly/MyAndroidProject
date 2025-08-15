package com.example.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


abstract class BaseViewBindingFragment<VB : ViewBinding> : BaseFragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding ?: throw IllegalStateException("ViewBinding没有初始化")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val type: Type? = javaClass.genericSuperclass
        if (type != null && type is ParameterizedType) {
            val clz = type.actualTypeArguments[0] as Class<*>
            val method = clz.getMethod(
                "inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java
            )
            _binding = method.invoke(null, inflater, container, false) as VB
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}