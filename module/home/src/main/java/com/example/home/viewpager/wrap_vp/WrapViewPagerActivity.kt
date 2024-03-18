package com.example.home.viewpager.wrap_vp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.widgets.layout.WrapContentViewPager

class WrapViewPagerActivity : BaseActivity() {
    private lateinit var wrapViewPager: WrapContentViewPager

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, WrapViewPagerActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrap_view_pager)
        initViews()
        initViewPager()
    }

    private fun initViews() {
        wrapViewPager = findViewById(R.id.wrap_view_pager)
    }

    private fun initViewPager() {
        wrapViewPager.adapter = MyAdapter(mContext)
    }

    class MyAdapter(private val context: Context) : PagerAdapter() {

        private val mInflater = LayoutInflater.from(context)

        override fun getCount(): Int {
            return 3
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imgIds = intArrayOf(R.drawable.apple_pic, R.drawable.a, R.drawable.bg)
            val view: FrameLayout =
                mInflater.inflate(R.layout.item_pager, container, false) as FrameLayout
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            imageView.setImageResource(imgIds[position])
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }
}