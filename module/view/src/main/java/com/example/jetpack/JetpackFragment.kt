package com.example.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.BaseFragment
import com.google.android.flexbox.FlexboxLayout

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
     }
}