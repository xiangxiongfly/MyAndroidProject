package com.example.jetpack.viewbinding.base1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityOneBinding

class OneActivity : BindingActivity<ActivityOneBinding>() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, OneActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        mViewBinding.tvName.text = "小白"
        mViewBinding.tvAge.text = 18.toString()
        mViewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getViewBinding(): ActivityOneBinding {
        return ActivityOneBinding.inflate(layoutInflater)
    }
}