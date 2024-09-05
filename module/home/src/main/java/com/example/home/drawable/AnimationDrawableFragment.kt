package com.example.home.drawable

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.base.BaseFragment
import com.example.home.R

class AnimationDrawableFragment : BaseFragment(R.layout.fragment_common_drawable) {
    private var isStart1 = false
    private var isStart2 = false

    private val drawables = intArrayOf(
        R.drawable.abcd,
        R.drawable.b,
        R.drawable.c,
        R.drawable.d,
        R.drawable.e,
        R.drawable.apple_pic,
        R.drawable.banana_pic,
        R.drawable.cherry_pic,
        R.drawable.mango_pic,
        R.drawable.grape_pic,
    )

    companion object {
        @JvmStatic
        fun newInstance() = AnimationDrawableFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val iv1 = view.findViewById<ImageView>(R.id.iv1)
        val iv2 = view.findViewById<ImageView>(R.id.iv2)

        val animationDrawable =
            ContextCompat.getDrawable(mContext, R.drawable.animation_drawable) as AnimationDrawable
        iv1.setImageDrawable(animationDrawable)

        val animationDrawable2 = AnimationDrawable().apply {
            drawables.forEachIndexed { index, item ->
                addFrame(ContextCompat.getDrawable(mContext, item)!!, 500)
            }
        }
        iv2.setImageDrawable(animationDrawable2)

        iv1.setOnClickListener {
            isStart1 = !isStart1
            if (isStart1) {
                animationDrawable.start()
            } else {
                if (animationDrawable.isRunning) animationDrawable.stop()
            }
        }
        iv2.setOnClickListener {
            isStart2 = !isStart2
            if (isStart2) {
                animationDrawable2.start()
            } else {
                if (animationDrawable2.isRunning) animationDrawable2.stop()
            }
        }
    }
}