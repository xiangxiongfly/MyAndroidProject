package com.example.home.drawable.custom

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.home.R
import com.xiangxiongfly.common.base.BaseFragment

class CustomCircleDrawableFragment : BaseFragment(R.layout.fragment_custom_circle_drawable) {

    companion object {
        @JvmStatic
        fun newInstance() = CustomCircleDrawableFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val drawable = CustomCircleDrawable()
        imageView.setImageDrawable(drawable)

        imageView.setOnClickListener {
            if (!drawable.isRunning) {
                drawable.start()
            } else {
                drawable.stop()
            }
        }
    }
}