package com.example.home.drawable

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.base.BaseFragment
import com.example.home.R

class ShapeDrawableFragment : BaseFragment(R.layout.fragment_common_drawable) {
    companion object {
        @JvmStatic
        fun newInstance() = ShapeDrawableFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val iv1 = view.findViewById<ImageView>(R.id.iv1)
        val iv2 = view.findViewById<ImageView>(R.id.iv2)

        val shapeDrawable = ContextCompat.getDrawable(mContext, R.drawable.shape_drawable)
        iv1.setImageDrawable(shapeDrawable)
    }
}