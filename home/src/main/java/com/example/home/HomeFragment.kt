package com.example.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.home.bottom_sheet.BottomSheetActivity
import com.example.home.drawable.DrawableActivity
import com.example.home.expandable_listview.ExpandableListViewActivity
import com.example.home.immersion.ImmersionActivity
import com.example.home.listview.ListViewActivity
import com.example.home.recyclerview.RecyclerViewActivity
import com.example.home.span.SpannableStringActivity
import com.example.home.tablayout.TabLayoutActivity
import com.example.home.viewpager.ViewPagerActivity
import com.example.home.viewpager2.ViewPager2Activity
import com.google.android.flexbox.FlexboxLayout
import com.xiangxiongfly.common.base.BaseFragment
import com.xiangxiongfly.common.exts.addElement

class HomeFragment : BaseFragment() {
    private lateinit var flexboxLayout: FlexboxLayout

    companion object {
        @JvmStatic
        fun newInstance(title: String) = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        addElements()
    }

    private fun initViews(view: View) {
        flexboxLayout = view.findViewById(R.id.flexboxLayout)
    }

    private fun addElements() {
        flexboxLayout.removeAllViews()
        flexboxLayout.addElement(mContext, "ViewPager", ViewPagerActivity::class.java)
        flexboxLayout.addElement(mContext, "ViewPager2", ViewPager2Activity::class.java)
        flexboxLayout.addElement(mContext, "BottomSheet", BottomSheetActivity::class.java)
        flexboxLayout.addElement(mContext, "沉浸式状态栏", ImmersionActivity::class.java)
        flexboxLayout.addElement(mContext, "ListView", ListViewActivity::class.java)
        flexboxLayout.addElement(
            mContext,
            "ExpandableListView",
            ExpandableListViewActivity::class.java
        )
        flexboxLayout.addElement(mContext, "RecyclerView", RecyclerViewActivity::class.java)
        flexboxLayout.addElement(mContext, "SpannableString", SpannableStringActivity::class.java)
        flexboxLayout.addElement(mContext, "Drawable", DrawableActivity::class.java)
        flexboxLayout.addElement(mContext, "TabLayout", TabLayoutActivity::class.java)
    }

}