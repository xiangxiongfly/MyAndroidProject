package com.example.androidui.viewpager.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.size
import androidx.viewpager.widget.ViewPager
import com.example.androidui.R
import com.example.androidui.viewpager.transformer.DepthPageTransformer
import com.example.androidui.viewpager.transformer.RotateTransformer
import com.example.androidui.viewpager.transformer.ScaleTransformer
import com.example.core.base.BaseActivity
import com.example.core.utils.dp

class ViewPagerSimpleActivity : BaseActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var indicator: LinearLayout
    private lateinit var viewPager2: ViewPager
    private lateinit var indicator2: LinearLayout
    private lateinit var dotSelected: View
    private lateinit var viewPager3: ViewPager
    private lateinit var viewPager4: ViewPager

    //图片资源
    private val mImgIds =
        intArrayOf(R.drawable.abcd, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e)

    //亮点之间距离
    private var distance = 0

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, ViewPagerSimpleActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_simple)
        initView()

        initViewPager1()
        initViewPager2()
        initViewPager3()
        initViewPager4()
    }

    private fun initView() {
        viewPager = findViewById(R.id.viewPager)
        indicator = findViewById(R.id.indicator)
        viewPager2 = findViewById(R.id.viewPager2)
        indicator2 = findViewById(R.id.indicator2)
        dotSelected = findViewById(R.id.dot_selected)
        viewPager3 = findViewById(R.id.viewPager3)
        viewPager4 = findViewById(R.id.viewPager4)
    }

    /**
     * 获取ImageView集合
     */
    private fun getImageViewList(): ArrayList<ImageView> {
        return arrayListOf<ImageView>().apply {
            for (i in mImgIds.indices) {
                val imageView = ImageView(context)
                imageView.scaleType = ImageView.ScaleType.FIT_XY
                imageView.setImageResource(mImgIds[i])
                this.add(imageView)
            }
        }
    }

    /**
     * ViewPager基本使用
     */
    private fun initViewPager1() {
        val imageViewList = getImageViewList()
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

    /**
     * ViewPager基本使用2+缩放透明动画
     */
    private fun initViewPager2() {
        val imageViewList = getImageViewList()
        val width = 10.dp

        for (i in mImgIds.indices) {
            val dot = View(this)
            dot.setBackgroundResource(R.drawable.dot_normal_shape)
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams(width, width))
            if (i != 0) {
                layoutParams.leftMargin = width
            }
            indicator2.addView(dot, layoutParams)
        }
        viewPager2.adapter = ViewPagerAdapter(imageViewList)
        dotSelected.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    dotSelected.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    distance = indicator2.getChildAt(1).left - indicator2.getChildAt(0).left
                }
            }
        )
        viewPager2.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val leftMargin: Int = (position * distance + positionOffset * distance).toInt()
                val layoutParams = dotSelected.layoutParams as FrameLayout.LayoutParams
                layoutParams.leftMargin = leftMargin
                dotSelected.layoutParams = layoutParams
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        //设置页面变形效果
        viewPager2.setPageTransformer(true, ScaleTransformer())
    }

    /**
     * ViewPager旋转动画
     */
    private fun initViewPager3() {
        val imageViewList = getImageViewList()
        viewPager3.offscreenPageLimit = 3
        viewPager3.pageMargin = 0
        viewPager3.adapter = ViewPagerAdapter(imageViewList)
        viewPager3.setPageTransformer(true, RotateTransformer())
    }

    /**
     * ViewPager景深动画
     */
    private fun initViewPager4() {
        val imageViewList = getImageViewList()
        viewPager4.adapter = ViewPagerAdapter(imageViewList)
        viewPager4.setPageTransformer(true, DepthPageTransformer())
    }
}