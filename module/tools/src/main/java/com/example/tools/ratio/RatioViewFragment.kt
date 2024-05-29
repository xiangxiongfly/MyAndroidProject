package com.example.tools.ratio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tools.R

class RatioViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ratio_view, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = RatioViewFragment()
    }
}