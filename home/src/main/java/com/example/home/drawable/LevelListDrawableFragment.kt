package com.example.home.drawable

import android.graphics.drawable.LevelListDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.content.ContextCompat
import com.example.home.R
import com.xiangxiongfly.common.base.BaseFragment


class LevelListDrawableFragment : BaseFragment(R.layout.fragment_level_list_drawable) {

    companion object {
        @JvmStatic
        fun newInstance() = LevelListDrawableFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val iv1 = view.findViewById<ImageView>(R.id.iv1)
        val iv2 = view.findViewById<ImageView>(R.id.iv2)
        val seekBar = view.findViewById<AppCompatSeekBar>(R.id.seekBar)

        val levelListDrawable =
            ContextCompat.getDrawable(mContext, R.drawable.level_list_drawable)!!
        iv1.setImageDrawable(levelListDrawable)

        val levelListDrawable2 = LevelListDrawable().apply {
            addLevel(0, 1, ContextCompat.getDrawable(mContext, R.drawable.a))
            addLevel(0, 2, ContextCompat.getDrawable(mContext, R.drawable.b))
            addLevel(0, 3, ContextCompat.getDrawable(mContext, R.drawable.c))
            addLevel(0, 4, ContextCompat.getDrawable(mContext, R.drawable.d))
            addLevel(0, 5, ContextCompat.getDrawable(mContext, R.drawable.e))
            addLevel(0, 6, ContextCompat.getDrawable(mContext, R.drawable.apple_pic))
            addLevel(0, 7, ContextCompat.getDrawable(mContext, R.drawable.banana_pic))
            addLevel(0, 8, ContextCompat.getDrawable(mContext, R.drawable.cherry_pic))
            addLevel(0, 9, ContextCompat.getDrawable(mContext, R.drawable.grape_pic))
            addLevel(0, 10, ContextCompat.getDrawable(mContext, R.drawable.mango_pic))
        }
        iv2.setImageDrawable(levelListDrawable2)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                levelListDrawable.level = progress
                levelListDrawable2.level = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}