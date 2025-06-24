package com.example.androidui.ratingbar

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import com.example.androidui.R
import com.example.core.base.BaseActivity
import com.example.core.utils.showToast

class RatingBarActivity : BaseActivity() {
    private lateinit var ratingBar: RatingBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_bar)
        initViews()
    }

    private fun initViews() {
        ratingBar = findViewById(R.id.rating_bar)
    }

    fun onGetScoreClick(view: View) {
        showToast(ratingBar.rating.toString())
    }
}