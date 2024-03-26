package com.example.home.viewpager.lazy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.base.BaseActivity
import com.example.home.R
import com.xiangxiongfly.common.fragment.TextFragment

/**
 * FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT：表示只有当Fragment显示在屏幕上时才执行onResume()
 * 可以将加载数据的方法放在onResume()中从而实现懒加载。
 */
class NewLazyActivity : BaseActivity() {
    private lateinit var viewPager: ViewPager

    private val fragments = arrayOf<TextFragment>(
        TextFragment.newInstance("AAA"),
        TextFragment.newInstance("BBB"),
        TextFragment.newInstance("CCC"),
        TextFragment.newInstance("DDD"),
        TextFragment.newInstance("EEE"),
        TextFragment.newInstance("FFF")
    )

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, NewLazyActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lazy)
        initView()

        viewPager.adapter = MyAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            fragments
        )
        viewPager.offscreenPageLimit = fragments.size
    }

    private fun initView() {
        viewPager = findViewById(R.id.viewPager)
    }

    class MyAdapter(
        fm: FragmentManager,
        behavior: Int,
        private val fragments: Array<TextFragment>
    ) : FragmentPagerAdapter(fm, behavior) {
        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }
    }
}

