package com.xiangxiongfly.common.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.xiangxiongfly.common.R
import com.xiangxiongfly.common.base.BaseFragment

class TextFragment : BaseFragment() {
    private lateinit var textView: TextView

    private var param: String? = null

    companion object {
        private const val ARG_PARAM = "param"

        @JvmStatic
        fun newInstance(param: String) =
            TextFragment().apply {
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
        return inflater.inflate(R.layout.fragment_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.textView)
        Log.e("TAG", "TextFragment onViewCreated $param")
    }

    override fun onResume() {
        super.onResume()
        Log.e("TAG", "TextFragment onResume $param")
        textView.text = "$param \n ${System.currentTimeMillis()}"
    }
}