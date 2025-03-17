package com.example.androidui.viewpager2.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.forEachIndexed
import androidx.viewpager2.widget.ViewPager2
import com.example.androidui.R
import com.example.core.base.BaseActivity
import com.example.core.base.KEY_TITLE
import com.example.core.utils.dp

class ViewPager2SimpleActivity : BaseActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var indicator: LinearLayout

    //图片资源
    private val mImgIds =
        intArrayOf(R.drawable.abcd, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e)

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, ViewPager2SimpleActivity::class.java).apply {
                putExtra(KEY_TITLE, "ViewPager2基本使用")
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2_simple)
        initView()
        initViewPager2()
    }

    private fun initView() {
        viewPager2 = findViewById(R.id.viewPager2)
        indicator = findViewById(R.id.indicator)
    }

    private fun initViewPager2() {
        val width = 10.dp

        for (i in mImgIds.indices) {
            val dot = View(this)
            dot.setBackgroundResource(R.drawable.dot_selector)
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams(width, width))
            if (i != 0) {
                layoutParams.leftMargin = width
            } else {
                dot.isSelected = true
            }
            indicator.addView(dot, layoutParams)
        }

        viewPager2.adapter = ViewPager2Adapter(this, mImgIds)
        //设置监听
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                indicator.forEachIndexed { index, view ->
                    view.isSelected = index == position
                }
            }
        })
    }
}