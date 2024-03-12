package com.example.home.viewpager.lazy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity

class LazyActivity : BaseActivity() {
    private lateinit var viewPager: ViewPager

    private val fragments = arrayOf<TestFragment>(
        TestFragment.newInstance("AAA"),
        TestFragment.newInstance("BBB"),
        TestFragment.newInstance("CCC"),
        TestFragment.newInstance("DDD")
    )

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, LazyActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lazy)
        initView()

        viewPager.adapter = MyAdapter(supportFragmentManager, fragments)
        viewPager.offscreenPageLimit = fragments.size
    }

    private fun initView() {
        viewPager = findViewById(R.id.viewPager)
    }

    class MyAdapter(fm: FragmentManager, private val fragments: Array<TestFragment>) :
        FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }
    }
}

