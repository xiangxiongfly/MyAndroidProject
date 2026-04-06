package com.example.androidui.viewpager2.fragmentadapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.androidui.R

class PageFragment : Fragment() {
    private var title = ""

    companion object {
        fun newInstance(title: String) =
            PageFragment().apply {
                arguments = Bundle().apply {
                    putString("title", title)
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
        Log.e("ViewPager2", "${title} onCreateView")
        return inflater.inflate(R.layout.item_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = view.findViewById(R.id.textView)
        textView.text = title
    }

    override fun onResume() {
        super.onResume()
        Log.e("ViewPager2", "${title} onResume")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("ViewPager2", "${title} onDestroyView")
    }
}