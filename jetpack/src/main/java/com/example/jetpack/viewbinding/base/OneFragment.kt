package com.example.jetpack.viewbinding.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.jetpack.R
import com.example.jetpack.databinding.FragmentOneBinding

class OneFragment : BindingFragment<FragmentOneBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOneBinding {
        return FragmentOneBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        mViewBinding.tvName.text = "小白"
        mViewBinding.tvAge.text = 18.toString()
        mViewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show()
        }
    }
}