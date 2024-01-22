package com.example.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jetpack.lifecycle.LifecycleActivity
import com.example.jetpack.livedata.LiveDataActivity
import com.example.jetpack.viewbinding.ViewBindingActivity
import com.example.jetpack.viewmodel.ViewModelActivity
import com.google.android.flexbox.FlexboxLayout
import com.xiangxiongfly.common.base.BaseFragment
import com.xiangxiongfly.common.exts.addElement

class JetpackFragment : BaseFragment() {
    private lateinit var flexboxLayout: FlexboxLayout

    companion object {
        @JvmStatic
        fun newInstance(title: String) = JetpackFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_jetpack, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        addElements()
    }

    private fun initViews(view: View) {
        flexboxLayout = view.findViewById(R.id.flexboxLayout)
    }

    private fun addElements() {
        flexboxLayout.removeAllViews()
        flexboxLayout.addElement(mContext, "Lifecycle", LifecycleActivity::class.java)
        flexboxLayout.addElement(mContext, "ViewModel", ViewModelActivity::class.java)
        flexboxLayout.addElement(mContext, "LiveData", LiveDataActivity::class.java)
        flexboxLayout.addElement(mContext, "ViewBinding", ViewBindingActivity::class.java)
    }
}