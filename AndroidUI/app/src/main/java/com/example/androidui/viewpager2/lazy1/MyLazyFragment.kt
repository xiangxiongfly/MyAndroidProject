package com.example.androidui.viewpager2.lazy1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.androidui.R

class MyLazyFragment : LazyFragment() {
    private lateinit var textView: TextView
    private var title = ""

    companion object {
        fun newInstance(title: String) = MyLazyFragment().apply {
            arguments = Bundle().apply {
                putString("title", title)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            title = it.getString("title", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_page_lazy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById<TextView>(R.id.textView)
        val btn = view.findViewById<Button>(R.id.btn)
        textView.text = title
        btn.setOnClickListener { onLazyLoad() }
    }

    override fun onLazyLoad() {
        Log.e("ViewPager2", "$title onLoadData")
        textView.text = "${System.currentTimeMillis()}"
    }

}