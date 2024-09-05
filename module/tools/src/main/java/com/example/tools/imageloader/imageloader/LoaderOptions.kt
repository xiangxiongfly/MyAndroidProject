package com.example.tools.imageloader.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes

class LoaderOptions {
    @DrawableRes
    var placeholder: Int = -1 // 占位图

    @DrawableRes
    var error: Int = -1 // 失败图

    var isMemoryCache = true // 是否内存缓存

    var isDiskCache = true //是否磁盘缓存

    var width: Int = -1 // 目标宽度

    var height: Int = -1 // 目标高度

    var url: String? = null // 目标url

    var targetObj: Any? = null

    var targetView: ImageView? = null // 目标ImageView

    constructor(targetObj: Any, url: String) {
        this.targetObj = targetObj
        this.url = url
    }

    fun placeholder(@DrawableRes placeholder: Int): LoaderOptions {
        this.placeholder = placeholder
        return this
    }

    fun error(@DrawableRes error: Int): LoaderOptions {
        this.error = error
        return this
    }

    fun override(size: Int): LoaderOptions {
        return override(size, size)
    }

    fun override(width: Int, height: Int): LoaderOptions {
        this.width = width
        this.height = height
        return this
    }

    fun isMemoryCache(isMemoryCache: Boolean): LoaderOptions {
        this.isMemoryCache = isMemoryCache
        return this
    }

    fun isDiskCache(isDiskCache: Boolean): LoaderOptions {
        this.isDiskCache = isDiskCache
        return this
    }


    fun into(imageView: ImageView) {
        this.targetView = imageView
        ImageLoader.with().loadOptions(this)
    }

}