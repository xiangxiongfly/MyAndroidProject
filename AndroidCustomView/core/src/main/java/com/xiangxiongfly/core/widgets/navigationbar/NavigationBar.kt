package com.xiangxiongfly.core.widgets.navigationbar

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import androidx.core.content.ContextCompat
import com.xiangxiongfly.core.widgets.navigationbar.TabIndex.Companion.ONE_INDEX
import com.xiangxiongfly.core.R
import com.xiangxiongfly.core.utils.dp

class NavigationBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), View.OnClickListener {
    private val lineView by lazy { createLine() }
    private val containerView by lazy { createContainer() }
    private var onItemSelectedListener: ((Int) -> Unit)? = null
    private var onItemReselectListener: ((Int) -> Unit)? = null
    private var currentIndex: Int = ONE_INDEX

    init {
        addView(containerView, 0)
        addView(lineView, 1)
    }

    fun setData(tabs: List<Tab>, defaultIndex: Int = ONE_INDEX) {
        containerView.removeAllViews()
        addViews(tabs)
        currentIndex = defaultIndex
        (containerView.getChildAt(currentIndex) as TabView).selected(true)
    }

    private fun addViews(tabs: List<Tab>) {
        tabs.let {
            for (tab in it) {
                containerView.addView(createTabView(tab))
            }
        }
    }

    private fun createTabView(tab: Tab): TabView {
        return TabView(context).apply {
            tag = tab.index
            setData(tab)
            setOnClickListener(this@NavigationBar)
        }
    }

    private fun createContainer(): LinearLayout {
        return LinearLayout(context).apply {
            layoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                    orientation = HORIZONTAL
                }
            gravity = Gravity.CENTER
        }
    }

    private fun createLine(): View {
        return View(context).apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.tab_line))
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, 1.dp, Gravity.TOP
            )
        }
    }

    override fun onClick(view: View) {
        if (view is TabView) {
            clickTabView(view)
        }
    }

    private var lock = false
    private fun clickTabView(view: View) {
        if (lock) {
            return
        }
        lock = true
        val selectIndex = view.tag as Int
        if (selectIndex == currentIndex) {
            onItemReselectListener?.invoke(selectIndex)
        } else {
            (containerView.getChildAt(currentIndex) as TabView).selected(false)
            (containerView.getChildAt(selectIndex) as TabView).selected(true)
            onItemSelectedListener?.invoke(selectIndex)
            currentIndex = selectIndex
        }
        lock = false
    }

    fun setOnItemSelectedListener(onItemSelectedListener: ((Int) -> Unit)) {
        this.onItemSelectedListener = onItemSelectedListener
    }

    fun setOnItemReselectListener(onItemReselectListener: ((Int) -> Unit)) {
        this.onItemReselectListener = onItemReselectListener
    }

}