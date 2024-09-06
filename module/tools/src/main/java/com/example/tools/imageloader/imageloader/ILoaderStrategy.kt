package com.example.tools.imageloader.imageloader

import android.content.Context
import android.widget.ImageView

interface ILoaderStrategy {

    fun loadImage(configs: ImageOptions)

    fun clearDiskCache(context: Context)

    fun clearMemoryCache(context: Context)

    fun clearAll(context: Context) {
        clearDiskCache(context)
        clearMemoryCache(context)
    }

    fun clear(imageView: ImageView)
}