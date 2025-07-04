package com.example.androidui

import android.os.Bundle
import com.example.androidui.bottom_sheet.BottomSheetActivity
import com.example.androidui.dialog.DialogActivity
import com.example.androidui.drawable.DrawableActivity
import com.example.androidui.expandablelistview.ExpandableListViewActivity
import com.example.androidui.layout.LayoutActivity
import com.example.androidui.listview.ListViewActivity
import com.example.androidui.popupwindow.PopupWindowActivity
import com.example.androidui.ratingbar.RatingBarActivity
import com.example.androidui.recyclerview.RecyclerViewActivity
import com.example.androidui.span.SpannableActivity
import com.example.androidui.spinner.SpinnerActivity
import com.example.androidui.tablayout.TabLayoutActivity
import com.example.androidui.textview.TextViewActivity
import com.example.androidui.viewpager.ViewPagerActivity
import com.example.androidui.viewpager2.ViewPager2Activity
import com.example.core.base.BaseActivity
import com.example.core.exts.addElement
import com.google.android.flexbox.FlexboxLayout

class MainActivity : BaseActivity() {
    private lateinit var flexboxLayout: FlexboxLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        addElements()
    }

    private fun initViews() {
        flexboxLayout = findViewById(R.id.flexboxLayout)
    }

    private fun addElements() {
        flexboxLayout.removeAllViews()
        flexboxLayout.addElement(context, "四大布局", LayoutActivity::class.java)
        flexboxLayout.addElement(context, "TextView", TextViewActivity::class.java)
        flexboxLayout.addElement(context, "Dialog", DialogActivity::class.java)
        flexboxLayout.addElement(context, "PopupWindow", PopupWindowActivity::class.java)
        flexboxLayout.addElement(context, "ViewPager", ViewPagerActivity::class.java)
        flexboxLayout.addElement(context, "ViewPager2", ViewPager2Activity::class.java)
        flexboxLayout.addElement(context, "BottomSheet", BottomSheetActivity::class.java)
        flexboxLayout.addElement(context, "ListView", ListViewActivity::class.java)
        flexboxLayout.addElement(
            context,
            "ExpandableListView",
            ExpandableListViewActivity::class.java
        )
        flexboxLayout.addElement(context, "RecyclerView", RecyclerViewActivity::class.java)
        flexboxLayout.addElement(context, "SpannableString", SpannableActivity::class.java)
        flexboxLayout.addElement(context, "Drawable", DrawableActivity::class.java)
        flexboxLayout.addElement(context, "TabLayout", TabLayoutActivity::class.java)
        flexboxLayout.addElement(context, "Spinner", SpinnerActivity::class.java)
        flexboxLayout.addElement(context, "RatingBar", RatingBarActivity::class.java)
    }

}