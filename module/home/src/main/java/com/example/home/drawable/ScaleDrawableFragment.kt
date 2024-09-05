package com.example.home.drawable

import android.graphics.drawable.ScaleDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.content.ContextCompat
import com.example.base.BaseFragment
import com.example.home.R

class ScaleDrawableFragment : BaseFragment(R.layout.fragment_scale_drawable) {

    companion object {
        @JvmStatic
        fun newInstance() = ScaleDrawableFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val seekBar = view.findViewById<AppCompatSeekBar>(R.id.seekBar)
        val iv1 = view.findViewById<ImageView>(R.id.iv1)
        val iv2 = view.findViewById<ImageView>(R.id.iv2)

        val scaleDrawable = ContextCompat.getDrawable(mContext, R.drawable.scale_drawable)!!
        iv1.setImageDrawable(scaleDrawable)

        val scaleDrawable2 =
            ScaleDrawable(ContextCompat.getDrawable(mContext, R.drawable.abcd), Gravity.CENTER, 1F, 1F)
        iv2.setImageDrawable(scaleDrawable2)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                scaleDrawable.level = progress
                scaleDrawable2.level = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


    }
}