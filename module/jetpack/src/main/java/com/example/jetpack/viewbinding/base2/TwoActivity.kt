package com.example.jetpack.viewbinding.base2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityTwoBinding

class TwoActivity : BindingActivity<ActivityTwoBinding>() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, TwoActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        mViewBinding.tvName.text = "小白"
        mViewBinding.tvAge.text = 28.toString()
        mViewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello2", Toast.LENGTH_SHORT).show()
        }
    }
}