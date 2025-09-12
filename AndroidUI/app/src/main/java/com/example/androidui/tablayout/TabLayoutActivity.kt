package com.example.androidui.tablayout

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.SimpleColorFilter
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback
import com.example.androidui.R
import com.example.core.base.BaseActivity
import com.example.core.fragment.SimpleFragment
import com.example.core.exts.dp
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutActivity : BaseActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout01: TabLayout
    private lateinit var tabLayout02: TabLayout
    private lateinit var tabLayout03: TabLayout
    private lateinit var tabLayout04: TabLayout
    private lateinit var tabLayout05: TabLayout
    private lateinit var tabLayout06: TabLayout

    private val titles = arrayOf<String>("Android", "Java", "前端", "Flutter")
    private val fragments = arrayOf<Fragment>(
        SimpleFragment.newInstance(titles[0]),
        SimpleFragment.newInstance(titles[1]),
        SimpleFragment.newInstance(titles[2]),
        SimpleFragment.newInstance(titles[3])
    )
    private val drawables = intArrayOf(
        R.drawable.ic_phone,
        R.drawable.ic_note,
        R.drawable.ic_email,
        R.drawable.ic_backup
    )

    private val defaultIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)
        initViews()
        initViewPager()
        initTabLayout01()
        initTabLayout02()
        initTabLayout03()
        initTabLayout04()
        initTabLayout05()
        initTabLayout06()
    }

    private fun initViews() {
        viewPager2 = findViewById(R.id.viewPager2)
        tabLayout01 = findViewById(R.id.tabLayout01)
        tabLayout02 = findViewById(R.id.tabLayout02)
        tabLayout03 = findViewById(R.id.tabLayout03)
        tabLayout04 = findViewById(R.id.tabLayout04)
        tabLayout05 = findViewById(R.id.tabLayout05)
        tabLayout06 = findViewById(R.id.tabLayout06)
    }

    private fun initViewPager() {
        viewPager2.adapter = object : FragmentStateAdapter(this@TabLayoutActivity) {
            override fun getItemCount(): Int {
                return fragments.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }
        }
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout06.getTabAt(position)?.select()
            }
        })
    }

    /**
     * 基本使用
     */
    private fun initTabLayout01() {
        TabLayoutMediator(
            tabLayout01,
            viewPager2,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = titles[position]
                }
            }).attach()
    }

    /**
     * 隐藏下划线，添加图标
     */
    private fun initTabLayout02() {
        TabLayoutMediator(
            tabLayout02,
            viewPager2,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = titles[position]
                }
            }).attach()
        for (i in 0..tabLayout02.tabCount) {
            tabLayout02.getTabAt(i)?.setIcon(drawables[i])
        }
        tabLayout02.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon?.selected()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon?.unselected()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        val defaultTab = tabLayout02.getTabAt(defaultIndex)
        defaultTab?.select()
        defaultTab?.icon?.selected()
    }

    //图片选中状态
    fun Drawable.selected() {
        this.setTint(ContextCompat.getColor(context, R.color.colorPrimary))
    }

    //图片未选中状态
    fun Drawable.unselected() {
        this.setTint(ContextCompat.getColor(context, R.color.grey))
    }

    /**
     * 自定义下划线，添加分割线
     */
    private fun initTabLayout03() {
        TabLayoutMediator(
            tabLayout03,
            viewPager2,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = titles[position]
                }
            }).attach()
        for (i in 0..tabLayout03.tabCount) {
            val linearLayout = tabLayout03.getChildAt(i) as? LinearLayout
            linearLayout?.apply {
                showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
                dividerDrawable =
                    ContextCompat.getDrawable(context, R.drawable.tab_divider)
                dividerPadding = 2.dp
            }
        }
        val defaultTab = tabLayout03.getTabAt(defaultIndex)
        defaultTab?.select()
    }

    /**
     * 角标
     */
    private fun initTabLayout04() {
        TabLayoutMediator(
            tabLayout04,
            viewPager2,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = titles[position]
                }
            }).attach()
        //数字角标
        tabLayout04.getTabAt(1)?.let {
            it.orCreateBadge.apply {
                backgroundColor = Color.RED
                maxCharacterCount = 3
                number = 99999
                badgeTextColor = Color.WHITE
            }
        }
        //红点
        tabLayout04.getTabAt(2)?.let {
            it.orCreateBadge.backgroundColor = ContextCompat.getColor(this, R.color.orange)
        }
        val defaultTab = tabLayout04.getTabAt(defaultIndex)
        defaultTab?.select()
    }

    /**
     * 圆角样式
     */
    private fun initTabLayout05() {
        TabLayoutMediator(
            tabLayout05,
            viewPager2,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = titles[position]
                }
            }).attach()
        val defaultTab = tabLayout05.getTabAt(defaultIndex)
        defaultTab?.select()
    }

    /**
     * 自定义View，添加Lottie动画
     */
    private fun initTabLayout06() {
        val layoutInflate = LayoutInflater.from(context)
        val map = mapOf<String, Int>(
            "apple" to R.raw.apple,
            "heart" to R.raw.heart,
            "sun_moon" to R.raw.sun_moon,
            "pizza" to R.raw.pizza
        )
        map.keys.forEach { s: String ->
            val tab = tabLayout06.newTab()
            val view = layoutInflate.inflate(R.layout.item_tab, null)
            val image = view.findViewById<LottieAnimationView>(R.id.tab_img).apply {
                setAnimation(map[s]!!)
                setColorFilter(Color.BLUE)
            }
            val text = view.findViewById<TextView>(R.id.tab_text).apply {
                text = s
            }
            tab.customView = view
            tabLayout06.addTab(tab)
        }
        tabLayout06.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.selected()
                tab?.let {
                    viewPager2.currentItem = it.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.unselect()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        val defaultTab = tabLayout06.getTabAt(defaultIndex)
        defaultTab?.select()
        defaultTab?.selected()
    }

    /**
     * 选中状态
     */
    fun TabLayout.Tab.selected() {
        this.customView?.let {
            val image = it.findViewById<LottieAnimationView>(R.id.tab_img)
            val text = it.findViewById<TextView>(R.id.tab_text)
            if (!image.isAnimating) image.playAnimation()
            setLottieColor(image, true)
            text.setTextColor(ContextCompat.getColor(context, R.color.blue))
        }
    }

    /**
     * 未选中状态
     */
    fun TabLayout.Tab.unselect() {
        this.customView?.let {
            val image = it.findViewById<LottieAnimationView>(R.id.tab_img)
            val text = it.findViewById<TextView>(R.id.tab_text)
            if (image.isAnimating) image.cancelAnimation()
            image.progress = 0F
            setLottieColor(image, false)
            text.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }

    /**
     * set lottie icon color
     */
    private fun setLottieColor(imageView: LottieAnimationView?, isSelected: Boolean) {
        imageView?.let {
            val color = if (isSelected) R.color.blue else R.color.black
            val csl = AppCompatResources.getColorStateList(this@TabLayoutActivity, color)
            val filter = SimpleColorFilter(csl.defaultColor)
            val keyPath = KeyPath("**")
            val callback = LottieValueCallback<ColorFilter>(filter)
            it.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback)
        }
    }

}
