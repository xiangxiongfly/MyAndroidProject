package com.example.tools.imageloader.imageloader

import android.content.Context

interface ILoaderStrategy {
    /**
     * 加载图片
     */
    fun loadImage(options: LoaderOptions)

    /**
     * 清除内存缓存
     */
    fun clearMemoryCache(context: Context)

    /**
     * 清除磁盘缓存
     */
    fun clearDiskCache(context: Context)

    /**
     * 清除所有缓存
     */
    fun clearAll(context: Context) {
        clearMemoryCache(context)
        clearDiskCache(context)
    }

    /**
     * 清除单个缓存
     */

}