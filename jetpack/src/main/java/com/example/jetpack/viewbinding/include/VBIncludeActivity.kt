package com.example.jetpack.viewbinding.include

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityVbincludeBinding
import com.example.jetpack.databinding.MergeDetailBinding
import com.xiangxiongfly.common.base.BaseActivity

class VBIncludeActivity : BaseActivity() {
    private var _viewBinding: ActivityVbincludeBinding? = null
    val viewBinding get() = _viewBinding!!

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, VBIncludeActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = ActivityVbincludeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //不使用merge
        viewBinding.titleBar.title.text = "这是一个标题"
        viewBinding.titleBar.back.setOnClickListener {
            Toast.makeText(mContext, "返回", Toast.LENGTH_SHORT).show()
        }
        viewBinding.titleBar.confirm.setOnClickListener {
            Toast.makeText(mContext, "确定", Toast.LENGTH_SHORT).show()
        }

        //使用merge
        val mergeDetailBinding = MergeDetailBinding.bind(viewBinding.root)
        mergeDetailBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        mergeDetailBinding.tvName.text = "小明"
        mergeDetailBinding.tvAge.text = 18.toString()
        mergeDetailBinding.tvAddress.text = "北京市海淀区"
    }
}