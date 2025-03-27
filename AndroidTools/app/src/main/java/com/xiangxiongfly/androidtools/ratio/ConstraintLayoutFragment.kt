package com.xiangxiongfly.androidtools.ratio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xiangxiongfly.androidtools.R

class ConstraintLayoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_constraint_layout, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConstraintLayoutFragment()
    }
}