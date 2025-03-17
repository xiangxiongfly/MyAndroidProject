package com.example.androidui.drawable

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.androidui.R
import com.example.core.base.BaseFragment
import com.example.core.utils.dp

class GradientDrawableFragment : BaseFragment(R.layout.fragment_common_drawable) {

    companion object {
        @JvmStatic
        fun newInstance() = GradientDrawableFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val iv1 = view.findViewById<ImageView>(R.id.iv1)
        val iv2 = view.findViewById<ImageView>(R.id.iv2)

        val gradientDrawable = ContextCompat.getDrawable(mContext, R.drawable.gradient_drawable)
        iv1.setImageDrawable(gradientDrawable)

        val gradientDrawable2 = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            gradientType = GradientDrawable.RADIAL_GRADIENT
            colors = intArrayOf(Color.parseColor("#00F5FF"), Color.parseColor("#BBFFFF"))
            gradientRadius = 100.dp.toFloat()
        }
        iv2.setImageDrawable(gradientDrawable2)
    }
}