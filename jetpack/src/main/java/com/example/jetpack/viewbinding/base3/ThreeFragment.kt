package com.example.jetpack.viewbinding.base3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.jetpack.R
import com.example.jetpack.databinding.FragmentThreeBinding
import com.xiangxiongfly.common.base.BaseFragment

class ThreeFragment : BaseFragment(R.layout.fragment_three) {
    private val viewBinding: FragmentThreeBinding by viewBinding(FragmentThreeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        viewBinding.tvName.text = "小白"
        viewBinding.tvAge.text = 38.toString()
        viewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello3", Toast.LENGTH_SHORT).show()
        }
    }
}