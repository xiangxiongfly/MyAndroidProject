package com.example.home.drawable

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.content.ContextCompat
import com.example.base.BaseFragment
import com.example.home.R

class ClipDrawableFragment : BaseFragment(R.layout.fragment_clip_drawable) {

    companion object {
        @JvmStatic
        fun newInstance() = ClipDrawableFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val seekBar = view.findViewById<AppCompatSeekBar>(R.id.seekBar)
        val iv1 = view.findViewById<ImageView>(R.id.iv1)
        val iv2 = view.findViewById<ImageView>(R.id.iv2)

        val clipDrawable = ContextCompat.getDrawable(mContext, R.drawable.clip_drawable)!!
        iv1.setImageDrawable(clipDrawable)

        val clipDrawable2 = ClipDrawable(
            ContextCompat.getDrawable(mContext, R.drawable.abcd),
            Gravity.CENTER,
            ClipDrawable.VERTICAL
        )
        iv2.setImageDrawable(clipDrawable2)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                clipDrawable.level = progress
                clipDrawable2.level = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


    }
}