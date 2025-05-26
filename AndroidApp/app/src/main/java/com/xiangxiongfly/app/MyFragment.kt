package com.xiangxiongfly.app

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MyFragment : Fragment() {
    private var title = ""

    companion object {
        fun newInstance(title: String): MyFragment {
            val args = Bundle().apply {
                putString("title", title)
            }
            return MyFragment().apply {
                arguments = args
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            title = it.getString("title") ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val textView = TextView(
            context
        ).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            gravity = Gravity.CENTER
            text = title
            textSize = 16F
        }
        return textView
    }
}