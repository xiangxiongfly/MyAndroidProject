package com.example.androidui.circleround

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.androidui.R
import com.example.androidui.databinding.ActivityCircleRoundBinding
import com.example.core.base.BaseViewBindingActivity
import com.example.core.exts.clipToCircleView
import com.example.core.exts.clipToRoundView
import com.example.core.exts.dp

class CircleRoundActivity : BaseViewBindingActivity<ActivityCircleRoundBinding>() {


    override fun initViews() {
        binding.ivClipCircle.clipToCircleView()
        binding.ivClipRound.clipToRoundView(10F.dp)

        Glide.with(this)
            .load(R.drawable.a)
            .transform(CircleCrop())
            .into(binding.ivGlideCircle)
        Glide.with(this)
            .load(R.drawable.a)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(10.dp)))
            .into(binding.ivGlideRound)


    }

    override fun initData() {

    }
}