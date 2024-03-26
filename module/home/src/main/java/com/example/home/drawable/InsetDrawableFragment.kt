package com.example.home.drawable

import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.base.BaseFragment
import com.example.base.utils.dp
import com.example.home.R

class InsetDrawableFragment : BaseFragment(R.layout.fragment_common_drawable) {

    companion object {
        @JvmStatic
        fun newInstance() = InsetDrawableFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val iv1 = view.findViewById<ImageView>(R.id.iv1)
        val iv2 = view.findViewById<ImageView>(R.id.iv2)

        val insetDrawable = ContextCompat.getDrawable(mContext, R.drawable.inset_drawable)
        iv1.setImageDrawable(insetDrawable)

        val insetDrawable2 = InsetDrawable(
            ContextCompat.getDrawable(mContext, R.drawable.a),
            300.dp
        )
        iv2.setImageDrawable(insetDrawable2)
    }
}