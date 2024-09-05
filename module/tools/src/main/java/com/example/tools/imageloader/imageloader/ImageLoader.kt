package com.example.tools.imageloader.imageloader

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class ImageLoader {

    private var imageLoader: ILoaderStrategy? = null

    companion object {

        private var instance: ImageLoader? = null

        fun with(): ImageLoader {
            if (instance == null) {
                synchronized(ImageLoader::class.java) {
                    if (instance == null) {
                        instance = ImageLoader()
                    }
                }
            }
            return instance!!
        }
    }

    fun setImageLoader(imageLoader: ILoaderStrategy) {
        this.imageLoader = imageLoader
    }

    fun load(context: Context, url: String): LoaderOptions {
        return LoaderOptions(context, url)
    }

    fun load(activity: FragmentActivity, url: String): LoaderOptions {
        return LoaderOptions(activity, url)
    }

    fun load(fragment: Fragment, url: String): LoaderOptions {
        return LoaderOptions(fragment, url)
    }

    fun load(view: View, url: String): LoaderOptions {
        return LoaderOptions(view, url)
    }

    fun loadOptions(options: LoaderOptions) {
        imageLoader!!.loadImage(options)
    }

    fun clearMemoryCache(context: Context) {
        imageLoader!!.clearMemoryCache(context)
    }

    fun clearDiskCache(context: Context) {
        imageLoader!!.clearDiskCache(context)
    }

    fun clearAll(context: Context) {
        imageLoader!!.clearAll(context)
    }

}