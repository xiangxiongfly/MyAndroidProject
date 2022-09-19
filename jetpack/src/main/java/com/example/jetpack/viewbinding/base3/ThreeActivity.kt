package com.example.jetpack.viewbinding.base3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityThreeBinding
import com.xiangxiongfly.common.base.BaseActivity

class ThreeActivity : BaseActivity() {
    private val viewBinding: ActivityThreeBinding by viewBinding(ActivityThreeBinding::inflate)

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ThreeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        viewBinding.tvName.text = "小白"
        viewBinding.tvAge.text = 38.toString()
        viewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello3", Toast.LENGTH_SHORT).show()
        }
    }
}