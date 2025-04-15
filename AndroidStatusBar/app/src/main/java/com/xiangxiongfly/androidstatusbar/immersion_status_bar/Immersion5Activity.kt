package com.xiangxiongfly.androidstatusbar.immersion_status_bar

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
import com.xiangxiongfly.androidstatusbar.base.BaseActivity
import com.xiangxiongfly.androidstatusbar.R
import com.xiangxiongfly.androidstatusbar.utils.BarUtils
import com.xiangxiongfly.androidstatusbar.utils.ScreenUtils

class Immersion5Activity : BaseActivity() {
    private lateinit var imageView: ImageView

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, Immersion5Activity::class.java))
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
        val bottom = BarUtils.getStatusBarHeight(this)

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
                            BarUtils.setDarkStatusBar(this@Immersion5Activity)
                        } else {
                            BarUtils.setLightStatusBar(this@Immersion5Activity)
                        }
                    }
                }
            })
    }
}