package com.example.home.drawable

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.base.BaseFragment
import com.example.home.R

class BitmapDrawableFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = BitmapDrawableFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_common_drawable, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val iv1 = view.findViewById<ImageView>(R.id.iv1)
        val iv2 = view.findViewById<ImageView>(R.id.iv2)

        val bitmapDrawable = ContextCompat.getDrawable(mContext, R.drawable.bitmap_drawable)
        iv1.setImageDrawable(bitmapDrawable)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.apple_pic)
        val bitmapDrawable2 = BitmapDrawable(resources, bitmap)
        iv2.setImageDrawable(bitmapDrawable2)
    }
}