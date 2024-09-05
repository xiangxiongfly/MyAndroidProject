package com.example.tools.imageloader

import android.os.Bundle
import android.widget.ImageView
import com.example.base.BaseActivity
import com.example.tools.R
import com.example.tools.imageloader.imageloader.GlideImageLoader
import com.example.tools.imageloader.imageloader.ImageLoader

class ImageLoaderActivity : BaseActivity() {
    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_loader)
        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)

        val url = "https://i-blog.csdnimg.cn/blog_migrate/de6e3262387d57977e53af596a87f582.png"

        ImageLoader.with().setImageLoader(GlideImageLoader())

//        ImageLoader.with().load(this, url).into(imageView1)

        ImageLoader.with().load(this, url)
//            .override(100)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView1)
    }

    override fun onDestroy() {
        super.onDestroy()

        ImageLoader.with().clearDiskCache(this)
        ImageLoader.with().clearMemoryCache(this)
    }
}

