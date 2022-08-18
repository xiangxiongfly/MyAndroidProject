package com.example.home.immersion

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE
import com.xiangxiongfly.common.utils.ScreenUtils
import com.xiangxiongfly.common.utils.StatusBarUtils

class ImmersionActivity5 : BaseActivity() {
    private lateinit var imageView: ImageView

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ImmersionActivity5::class.java).apply {
                putExtra(KEY_TITLE, "区分状态栏图标颜色和背景图颜色")
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_immersion5)
        val btnDarkImage = findViewById<Button>(R.id.btn_dark_image)
        val btnLightImage = findViewById<Button>(R.id.btn_light_image)
        imageView = findViewById(R.id.imageView)
        btnDarkImage.setOnClickListener {
            setImageByResource(R.drawable.dark_image)
        }
        btnLightImage.setOnClickListener {
            setImageByResource(R.drawable.light_image)
        }

        setImageByResource(R.drawable.light_image)
    }

    private fun setImageByResource(@DrawableRes imageResource: Int) {
        val bitmap = BitmapFactory.decodeResource(resources, imageResource)
        imageView.setImageBitmap(bitmap)
        detectBitmapColor(bitmap)
    }

    /**
     * 检测Bitmap颜色
     */
    private fun detectBitmapColor(bitmap: Bitmap) {
        val colorCount = 5
        val left = 0
        val top = 0
        val right = ScreenUtils.getScreenWidth(this)
        val bottom = StatusBarUtils.getStatusBarHeight(this)

        Palette.from(bitmap)
            .maximumColorCount(colorCount)
            .setRegion(left, top, right, bottom)
            .generate(object : Palette.PaletteAsyncListener {
                override fun onGenerated(palette: Palette?) {
                    var mostPopularSwatch: Palette.Swatch? = null
                    if (palette != null) {
                        for (swatch in palette.swatches) {
                            if (mostPopularSwatch == null
                                || swatch.population > mostPopularSwatch.population
                            ) {
                                mostPopularSwatch = swatch
                            }
                        }
                    }
                    mostPopularSwatch?.let { swatch ->
                        val luminance = ColorUtils.calculateLuminance(swatch.rgb)
                        if (luminance < 0.5) {
                            StatusBarUtils.setDarkStatusBar(mActivity)
                        } else {
                            StatusBarUtils.setLightStatusBar(mActivity)
                        }
                    }
                }
            })
    }
}