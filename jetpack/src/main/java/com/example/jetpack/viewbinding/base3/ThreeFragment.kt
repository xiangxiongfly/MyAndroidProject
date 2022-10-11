package com.example.jetpack.viewbinding.base3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.jetpack.R
import com.example.jetpack.databinding.FragmentThreeBinding
import com.xiangxiongfly.common.base.BaseFragment

class ThreeFragment : BaseFragment(R.layout.fragment_three) {
//    private val viewBinding: FragmentThreeBinding by viewBindings(FragmentThreeBinding::bind)
    private val viewBinding: FragmentThreeBinding by viewBindings()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        viewBinding.tvName.text = "小白33"
        viewBinding.tvAge.text = 338.toString()
        viewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello33", Toast.LENGTH_SHORT).show()
        }
    }
}