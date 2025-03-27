package com.xiangxiongfly.common.utils.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes

class ImageOptions private constructor() {
    var targetObj: Any? = null // 生命周期对象
    var targetView: ImageView? = null // 目标ImageView
    var resource: Any? = null // 加载资源
    var width = -1 // 指定宽度
    var height = -1 // 指定高度
    var isDiskCache = true // 磁盘缓存
    var isMemoryCache = true // 内存缓存

    @DrawableRes
    var placeholder: Int = -1 // 占位图资源

    @DrawableRes
    var error: Int = -1 // 失败图资源

    var listener: Listener? = null

    companion object {
        fun create(): ImageOptions {
            return ImageOptions()
        }
    }

    fun with(targetObj: Any): ImageOptions {
        this.targetObj = targetObj
        return this
    }

    fun loadResource(resource: Any): ImageOptions {
        this.resource = resource
        return this
    }

    fun size(size: Int): ImageOptions {
        return size(width, height)
    }

    fun size(width: Int, height: Int): ImageOptions {
        this.width = width
        this.height = height
        return this
    }

    fun placeholder(@DrawableRes placeholder: Int): ImageOptions {
        this.placeholder = placeholder
        return this
    }

    fun error(@DrawableRes error: Int): ImageOptions {
        this.error = error
        return this
    }

    fun setDiskCache(isCache: Boolean): ImageOptions {
        isDiskCache = isCache
        return this
    }

    fun setMemoryCache(isCache: Boolean): ImageOptions {
        isMemoryCache = isCache
        return this
    }

    fun setListener(listener: Listener): ImageOptions {
        this.listener = listener
        return this
    }

    fun into(imageView: ImageView) {
        this.targetView = imageView
        ImageLoader.loadOptions(this)
    }

    interface Listener {
        fun onSuccess(model: Any?)
        fun onFail(model: Any?)
    }
}