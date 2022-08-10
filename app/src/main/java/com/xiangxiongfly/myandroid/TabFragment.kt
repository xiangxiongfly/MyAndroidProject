package com.xiangxiongfly.myandroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val KEY_TITLE = "key_title"

class TabFragment : Fragment() {
    private lateinit var tvTitle: TextView

    private var mTitle: String? = null

    companion object {
        @JvmStatic
        fun newInstance(title: String) =
            TabFragment().apply {
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

        Log.e("TAG", "onCreate " + mTitle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle = view.findViewById(R.id.tv_title)
        tvTitle.text = mTitle
        tvTitle.setOnClickListener {
            tvTitle.text = mTitle + "改变了"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("TAG", "onDestroy " + mTitle)
    }
}