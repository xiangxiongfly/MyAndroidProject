package com.example.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.xiangxiongfly.common.base.BaseFragment


private const val KEY_TITLE = "key_title"

class JetpackFragment : BaseFragment() {
    private lateinit var tvTitle: TextView

    private var mTitle: String? = null

    companion object {
        @JvmStatic
        fun newInstance(title: String) =
            JetpackFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TITLE, title)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mTitle = it.getString(KEY_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_jetpack, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle = view.findViewById(R.id.tv_title)
        tvTitle.text = mTitle
        tvTitle.setOnClickListener {
            tvTitle.text = mTitle + "改变了"
        }
    }
}