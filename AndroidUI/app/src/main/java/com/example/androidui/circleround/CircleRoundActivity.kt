package com.example.androidui.circleround

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
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

        binding.iv1.post {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.a)
            val scaleBitmap =
                centerCropBitmap(bitmap, binding.iv1.measuredWidth, binding.iv1.measuredHeight)
            val circleDrawable = RoundedBitmapDrawableFactory.create(resources, scaleBitmap).apply {
                paint.isAntiAlias = true
                isCircular = true
            }
            binding.iv1.setImageDrawable(circleDrawable)
        }
        binding.iv2.post {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.a)
            val scaleBitmap =
                centerCropBitmap(bitmap, binding.iv2.measuredWidth, binding.iv2.measuredHeight)
            val roundDrawable = RoundedBitmapDrawableFactory.create(resources, scaleBitmap).apply {
                paint.isAntiAlias = true
                setCornerRadius(10F.dp)
            }
            binding.iv2.setImageDrawable(roundDrawable)
        }
    }

    override fun initData() {
    }

    private fun centerCropBitmap(source: Bitmap, targetWidth: Int, targetHeight: Int): Bitmap {
        val sourceWidth = source.width
        val sourceHeight = source.height

        // 计算缩放比例
        val scale = maxOf(
            targetWidth.toFloat() / sourceWidth,
            targetHeight.toFloat() / sourceHeight
        )

        // 计算缩放后的尺寸
        val scaledWidth = (sourceWidth * scale).toInt()
        val scaledHeight = (sourceHeight * scale).toInt()

        // 创建缩放后的 Bitmap
        val scaledBitmap = Bitmap.createScaledBitmap(source, scaledWidth, scaledHeight, true)

        // 计算裁剪位置
        val left = (scaledWidth - targetWidth) / 2
        val top = (scaledHeight - targetHeight) / 2

        // 裁剪 Bitmap
        return Bitmap.createBitmap(
            scaledBitmap,
            left.coerceAtLeast(0),
            top.coerceAtLeast(0),
            minOf(targetWidth, scaledWidth),
            minOf(targetHeight, scaledHeight)
        )
    }
}