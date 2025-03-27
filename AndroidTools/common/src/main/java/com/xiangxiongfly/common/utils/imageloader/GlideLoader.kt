package com.xiangxiongfly.common.utils.imageloader

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.File
import kotlin.concurrent.thread

class GlideLoader : ILoaderStrategy {
    private lateinit var requestManager: RequestManager

    override fun loadImage(options: ImageOptions) {
        requestManager = getRequestManager(options.targetObj)
        var requestBuilder: RequestBuilder<Drawable>? = null

        options.resource?.let {
            requestBuilder = generateRequestBuilder(it)
        }

        requestBuilder?.let {
            if (options.placeholder != -1) {
                it.placeholder(options.placeholder)
            }
            if (options.error != -1) {
                it.error(options.error)
            }
            if (options.width != -1 || options.height != -1) {
                it.override(options.width, options.height)
            }
            it.skipMemoryCache(options.isMemoryCache)
            it.diskCacheStrategy(if (options.isDiskCache) DiskCacheStrategy.AUTOMATIC else DiskCacheStrategy.NONE)
            options.listener?.let { listener ->
                it.addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        listener.onFail(model)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        listener.onSuccess(model)
                        return false
                    }
                })
            }
            if (options.targetView == null) {
                throw IllegalArgumentException("targetView cannot be null");
            }
            it.into(options.targetView!!)
        }
    }

    private fun getRequestManager(targetObj: Any?): RequestManager {
        return when (targetObj) {
            is FragmentActivity -> {
                Glide.with(targetObj)
            }
            is Context -> {
                Glide.with(targetObj)
            }
            is View -> {
                Glide.with(targetObj)
            }
            is Fragment -> {
                Glide.with(targetObj)
            }
            else -> {
                throw IllegalArgumentException("You cannot start a load on a null Context");
            }
        }
    }

    private fun generateRequestBuilder(res: Any): RequestBuilder<Drawable>? {
        return if (res is String) {
            requestManager.load(res)
        } else if (res is File) {
            requestManager.load(res)
        } else {
            return null
        }
    }

    override fun clearDiskCache(context: Context) {
        thread {
            Glide.get(context).clearDiskCache()
        }
    }

    override fun clearMemoryCache(context: Context) {
        Glide.get(context).clearMemory()
    }

    override fun clear(imageView: ImageView) {
        Glide.with(imageView.context).clear(imageView)
    }
}