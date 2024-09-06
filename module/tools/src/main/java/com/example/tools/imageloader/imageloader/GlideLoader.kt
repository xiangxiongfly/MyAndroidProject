package com.example.tools.imageloader.imageloader

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
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
            if (options.targetView == null) {
                throw IllegalArgumentException("targetView cannot be null");
            }
            it.into(options.targetView!!)
        }
    }

    private fun getRequestManager(targetObj: Any?): RequestManager {
        return if (targetObj is FragmentActivity) {
            Glide.with(targetObj)
        } else if (targetObj is Context) {
            Glide.with(targetObj)
        } else if (targetObj is View) {
            Glide.with(targetObj)
        } else if (targetObj is Fragment) {
            Glide.with(targetObj)
        } else {
            throw IllegalArgumentException("You cannot start a load on a null Context");
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
            Glide.get(context).clearMemory()
        }
    }

    override fun clearMemoryCache(context: Context) {
        Glide.get(context).clearMemory()
    }

    override fun clear(imageView: ImageView) {
        Glide.with(imageView.context).clear(imageView)
    }
}