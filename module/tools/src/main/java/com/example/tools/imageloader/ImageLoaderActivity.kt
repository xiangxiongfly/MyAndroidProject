package com.example.tools.imageloader

import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.base.BaseActivity
import com.example.tools.R
import com.example.tools.imageloader.imageloader.GlideLoader
import com.example.tools.imageloader.imageloader.ImageLoader
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
            .into(imageView1)

        ImageLoader.with(this)
            .loadResource(File(cacheDir, "aaa.png"))
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView2)

    }

    override fun onDestroy() {
        super.onDestroy()
        ImageLoader.clear(imageView1)
        ImageLoader.clearAll(this)
        ImageLoader.clearMemoryCache(this)
        ImageLoader.clearDiskCache(this)
    }
}

