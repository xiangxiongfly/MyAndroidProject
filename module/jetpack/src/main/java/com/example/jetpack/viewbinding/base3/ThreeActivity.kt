package com.example.jetpack.viewbinding.base3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.base.BaseActivity
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityThreeBinding


class ThreeActivity : BaseActivity() {
//        private val viewBinding: ActivityThreeBinding by viewBindings(ActivityThreeBinding::inflate)
    private val viewBinding: ActivityThreeBinding by viewBindings()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ThreeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        viewBinding.tvName.text = "小白3"
        viewBinding.tvAge.text = 38.toString()
        viewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello3", Toast.LENGTH_SHORT).show()
        }
    }
}