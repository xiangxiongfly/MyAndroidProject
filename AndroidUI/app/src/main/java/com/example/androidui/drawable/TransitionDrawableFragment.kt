package com.example.androidui.drawable

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.androidui.R
import com.example.core.base.BaseFragment

class TransitionDrawableFragment : BaseFragment(R.layout.fragment_common_drawable) {
    private var isShow1 = false
    private var isShow2 = false

    companion object {
        @JvmStatic
        fun newInstance() = TransitionDrawableFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val iv1 = view.findViewById<ImageView>(R.id.iv1)
        val iv2 = view.findViewById<ImageView>(R.id.iv2)

        val transitionDrawable = ContextCompat.getDrawable(
            mContext, R.drawable.transition_drawable
        ) as TransitionDrawable
        iv1.setImageDrawable(transitionDrawable)

        val drawables = arrayOf(
            ContextCompat.getDrawable(mContext, R.drawable.abcd),
            ContextCompat.getDrawable(mContext, R.drawable.b)
        )
        val transitionDrawable2 = TransitionDrawable(drawables)
        iv2.setImageDrawable(transitionDrawable2)

        iv1.setOnClickListener {
            isShow1 = !isShow1
            if (isShow1) {
                transitionDrawable.startTransition(3000)
            } else {
                transitionDrawable.reverseTransition(3000)
            }
        }

        iv2.setOnClickListener {
            isShow2 = !isShow2
            if (isShow2) {
                transitionDrawable2.startTransition(3000)
            } else {
                transitionDrawable2.reverseTransition(3000)
            }
        }

    }
}