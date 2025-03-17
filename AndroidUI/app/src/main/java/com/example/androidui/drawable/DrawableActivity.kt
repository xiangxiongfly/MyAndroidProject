package com.example.androidui.drawable

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.androidui.R
import com.example.androidui.drawable.custom.CustomCircleDrawableFragment
import com.example.core.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DrawableActivity : BaseActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    private val titles = arrayOf<String>(
        "BitmapDrawable",
        "LayerDrawable",
        "StateListDrawable",
        "LevelListDrawable",
        "TransitionDrawable",
        "InsetDrawable",
        "ClipDrawable",
        "ScaleDrawable",
        "ShapeDrawable",
        "GradientDrawable",
        "AnimationDrawable",
        "自定义Drawable",
    )

    private val tabFragments = arrayOf<Fragment>(
        BitmapDrawableFragment.newInstance(),
        LayerDrawableFragment.newInstance(),
        StateListDrawableFragment.newInstance(),
        LevelListDrawableFragment.newInstance(),
        TransitionDrawableFragment.newInstance(),
        InsetDrawableFragment.newInstance(),
        ClipDrawableFragment.newInstance(),
        ScaleDrawableFragment.newInstance(),
        ShapeDrawableFragment.newInstance(),
        GradientDrawableFragment.newInstance(),
        AnimationDrawableFragment.newInstance(),
        CustomCircleDrawableFragment.newInstance(),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable)
        initView()
        initViewPager2()
    }

    private fun initView() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager2)
    }

    private fun initViewPager2() {
        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = tabFragments.size
            override fun createFragment(position: Int): Fragment = tabFragments[position]
        }
        TabLayoutMediator(
            tabLayout,
            viewPager2
        ) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }
}