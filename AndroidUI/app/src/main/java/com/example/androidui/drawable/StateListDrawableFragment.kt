package com.example.androidui.drawable

import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.util.StateSet
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.androidui.R
import com.example.core.base.BaseFragment

class StateListDrawableFragment : BaseFragment(R.layout.fragment_common_drawable) {

    companion object {
        @JvmStatic
        fun newInstance() = StateListDrawableFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val iv1 = view.findViewById<ImageView>(R.id.iv1)
        val iv2 = view.findViewById<ImageView>(R.id.iv2)

        val stateListDrawable = ContextCompat.getDrawable(mContext, R.drawable.state_list_drawable)
        iv1.setImageDrawable(stateListDrawable)

        val stateListDrawable2 = StateListDrawable().apply {
            addState(
                intArrayOf(android.R.attr.state_pressed),
                ContextCompat.getDrawable(mContext, R.drawable.a)
            )
            addState(StateSet.NOTHING, ContextCompat.getDrawable(mContext, R.drawable.b))
        }
        iv2.setImageDrawable(stateListDrawable2)
    }
}