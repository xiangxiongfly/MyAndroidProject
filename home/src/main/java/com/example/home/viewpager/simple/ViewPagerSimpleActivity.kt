package com.example.home.viewpager.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.view.size
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.utils.dp2px

class ViewPagerSimpleActivity : BaseActivity() {
    private val mImgIds =
        intArrayOf(R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e)
    private lateinit var viewPager: ViewPager
    private lateinit var indicator: LinearLayout

    private val dotView = listOf<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_simple)
        initView()
        initViewPager1()
    }

    private fun initView() {
        viewPager = findViewById(R.id.viewPager)
        indicator = findViewById(R.id.indicator)
    }

    private fun initViewPager1() {
        val imageViewList = arrayListOf<ImageView>().apply {
            for (i in mImgIds.indices) {
                val imageView = ImageView(context)
                imageView.scaleType = ImageView.ScaleType.FIT_XY
                imageView.setImageResource(mImgIds[i])
                this.add(imageView)
            }
        }
        val width = dp2px(10)
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
        viewPager.adapter = ViewPagerAdapter(imageViewList)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until indicator.size) {
                    indicator.getChildAt(i).isSelected = i == position
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }
}
