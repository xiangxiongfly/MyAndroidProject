package com.xiangxiongfly.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.xiangxiongfly.core.R
import com.xiangxiongfly.core.base.BaseFragment

class SimpleFragment private constructor() : BaseFragment() {
    private lateinit var textView: TextView
    private var param: String? = null

    companion object {
        private const val ARG_PARAM = "param"

        @JvmStatic
        fun newInstance(param: String) =
            SimpleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, param)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_simple, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.textView)
        textView.text = "$param"
    }
}