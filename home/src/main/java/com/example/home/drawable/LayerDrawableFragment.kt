package com.example.home.drawable

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.home.R
import com.xiangxiongfly.common.base.BaseFragment
import com.xiangxiongfly.common.utils.dp

class LayerDrawableFragment : BaseFragment(R.layout.fragment_common_drawable) {

    companion object {
        @JvmStatic
        fun newInstance() = LayerDrawableFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val iv1 = view.findViewById<ImageView>(R.id.iv1)
        val iv2 = view.findViewById<ImageView>(R.id.iv2)

        val layerDrawable = ContextCompat.getDrawable(mContext, R.drawable.layer_drawable)
        iv1.setImageDrawable(layerDrawable)

        val drawable = ContextCompat.getDrawable(mContext, R.drawable.a)
        val rectDrawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.parseColor("#FF0000"))
        }
        val ovalDrawable = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(Color.parseColor("#00FF00"))
        }
        val ringDrawable = GradientDrawable().apply {
            setSize(50.dp, 50.dp)
            shape = GradientDrawable.OVAL
            setStroke(5.dp, Color.parseColor("#0000FF"))
        }
        val arr = arrayOf(drawable, rectDrawable, ovalDrawable, ringDrawable)
        val layerDrawable2 = LayerDrawable(arr).apply {
            setLayerInset(1, 10.dp, 10.dp, 10.dp, 10.dp)
            setLayerInset(2, 20.dp, 20.dp, 20.dp, 20.dp)
            setLayerGravity(3, Gravity.CENTER)
        }
        iv2.setImageDrawable(layerDrawable2)
    }
}