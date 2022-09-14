package com.example.jetpack.viewmodel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.jetpack.R
import com.xiangxiongfly.common.base.BaseFragment

class DetailFragment : BaseFragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.textView)
        sharedViewModel.liveData.observe(viewLifecycleOwner, object : Observer<String> {
            override fun onChanged(t: String?) {
                textView.text = t
            }
        })
    }
}