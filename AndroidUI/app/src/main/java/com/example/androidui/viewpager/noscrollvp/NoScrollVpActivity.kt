package com.example.androidui.viewpager.noscrollvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.androidui.R
import com.example.core.base.BaseActivity
import com.example.core.fragment.SimpleFragment

class NoScrollVpActivity : BaseActivity() {
    private lateinit var btnOne: Button
    private lateinit var btnTwo: Button
    private lateinit var btnThree: Button
    private lateinit var noScrollVp: NoScrollViewPager

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, NoScrollVpActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_scroll_vp)
        initViews()
        initVp()
    }

    private fun initViews() {
        btnOne = findViewById(R.id.btn_one)
        btnTwo = findViewById(R.id.btn_two)
        btnThree = findViewById(R.id.btn_three)
        noScrollVp = findViewById(R.id.no_scroll_vp)

        btnOne.setOnClickListener { noScrollVp.setCurrentItem(0, false) }
        btnTwo.setOnClickListener { noScrollVp.setCurrentItem(1, false) }
        btnThree.setOnClickListener { noScrollVp.setCurrentItem(2, false) }
    }

    private fun initVp() {
        val fragmentList = ArrayList<SimpleFragment>()
        fragmentList.add(SimpleFragment.newInstance("One"))
        fragmentList.add(SimpleFragment.newInstance("Two"))
        fragmentList.add(SimpleFragment.newInstance("Three"))
        noScrollVp.adapter = MyAdapter(supportFragmentManager, fragmentList)
    }

    class MyAdapter(
        fm: FragmentManager,
        private val fragmentList: ArrayList<SimpleFragment>
    ) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }
    }
}

