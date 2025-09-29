package com.xiangxiongfly.customview.ratingbar

import android.os.Bundle
import com.xiangxiongfly.core.base.BaseActivity
import com.xiangxiongfly.core.utils.showToast
import com.xiangxiongfly.core.widgets.ratingbar.RatingBar
import com.xiangxiongfly.customview.R

class RatingBarActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_bar)
        val ratingBar = findViewById<RatingBar>(R.id.rating_bar)
        ratingBar.setOnRatingBarChange { value, isTouch ->
            showToast("当前评分:$value")
        }
    }
}
