package com.example.jetpack.viewbinding.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.base.BaseActivity
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityVbBinding

class VBActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, VBActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityVbBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.tvName.text = "hello world"
        viewBinding.tvAge.text = 18.toString()
        viewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        viewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show()
        }

    }
}