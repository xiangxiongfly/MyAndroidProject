package com.example.tools.imageloader.imageloader

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager

class GlideImageLoader : ILoaderStrategy {
    override fun loadImage(options: LoaderOptions) {
        val requestManager = getRequestManager(options.targetObj)
        var requestBuilder: RequestBuilder<Drawable>? = null
        if (options.url != null) {
            requestBuilder = getRequestBuilder(requestManager, options.url!!)
        }
        requestBuilder?.let {
            if (options.width != -1 || options.height != -1) {
                it.override(options.width, options.height)
            }
            if (options.placeholder != -1) {
                it.placeholder(options.placeholder)
            }
            if (options.error != -1) {
                it.error(options.error)
            }
            it.into(options.targetView!!)
        }
    }

    private fun getRequestManager(targetObj: Any?): RequestManager {
        return if (targetObj is Context) {
            if (targetObj is FragmentActivity) {
                Glide.with(targetObj as FragmentActivity)
            } else {
                Glide.with(targetObj as Context)
            }
        } else if (targetObj is Fragment) {
            Glide.with(targetObj as Fragment)
        } else if (targetObj is View) {
            Glide.with(targetObj as View)
        } else {
            throw IllegalArgumentException("You cannot start a load on a null Context");
        }
    }

    private fun getRequestBuilder(
        requestManager: RequestManager,
        url: String
    ): RequestBuilder<Drawable> {
        return load(requestManager, url)
    }

    private fun load(request: RequestManager, url: String): RequestBuilder<Drawable> {
        return request.load(url)
    }

    override fun clearMemoryCache(context: Context) {
        Glide.get(context).clearMemory()
    }

    override fun clearDiskCache(context: Context) {
        Thread {
            Glide.get(context).clearDiskCache()
        }.start()
    }


}