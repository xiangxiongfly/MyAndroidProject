package com.example.tools.imageloader.imageloader

import android.content.Context
import android.widget.ImageView

object ImageLoader {

    private var imageLoader: ILoaderStrategy? = null

    fun setImageLoader(imageLoader: ILoaderStrategy) {
        this.imageLoader = imageLoader
    }

    fun with(targetObj: Any): ImageOptions {
        val options = ImageOptions.create()
        options.with(targetObj)
        return options
    }

    fun loadOptions(options: ImageOptions) {
        imageLoader?.loadImage(options)
    }

    fun clearDiskCache(context: Context) {
        imageLoader?.clearDiskCache(context)
    }

    fun clearMemoryCache(context: Context) {
        imageLoader?.clearMemoryCache(context)
    }

    fun clearAll(context: Context) {
        imageLoader?.clearAll(context)
    }

    fun clear(imageView: ImageView) {
        imageLoader?.clear(imageView)
    }

}