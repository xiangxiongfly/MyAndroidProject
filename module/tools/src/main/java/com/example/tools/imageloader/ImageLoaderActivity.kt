package com.example.tools.imageloader

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.base.BaseActivity
import com.example.tools.R
import com.example.tools.imageloader.imageloader.GlideLoader
import com.example.tools.imageloader.imageloader.ImageLoader
import com.example.tools.imageloader.imageloader.ImageOptions
import java.io.File

class ImageLoaderActivity : BaseActivity() {
    private lateinit var imageView0: ImageView
    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_loader)
        imageView0 = findViewById(R.id.imageView0)
        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)

        val url = "https://i-blog.csdnimg.cn/blog_migrate/de6e3262387d57977e53af596a87f582.png"

        Glide.with(this)
            .load(url)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView0)

        ImageLoader.setImageLoader(GlideLoader())

        ImageLoader.with(this)
            .loadResource(url)
            .size(100)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .setListener(object : ImageOptions.Listener {
                override fun onSuccess(model: Any?) {
                    Log.e("TAG", "成功")
                }

                override fun onFail(model: Any?) {
                    Log.e("TAG", "失败")
                }
            })
            .into(imageView1)

        ImageLoader.with(this)
            .loadResource(File(cacheDir, "aaa.jpg"))
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .setListener(object : ImageOptions.Listener {
                override fun onSuccess(model: Any?) {
                    Log.e("TAG", "成功2")
                }

                override fun onFail(model: Any?) {
                    Log.e("TAG", "失败2")
                }
            })
            .into(imageView2)
    }

    fun onClick(view: View) {
        ImageLoader.clearMemoryCache(this)
        ImageLoader.clearDiskCache(this)
        ImageLoader.clearAll(this)
    }

    fun onClick2(view: View) {
        ImageLoader.clear(imageView1)
    }
}

