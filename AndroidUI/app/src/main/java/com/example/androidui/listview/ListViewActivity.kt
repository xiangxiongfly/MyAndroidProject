package com.example.androidui.listview

import android.os.Bundle
import android.view.View
import com.example.androidui.R
import com.example.core.base.BaseActivity

class ListViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
    }

    fun toArrayAdapter(v: View) {
        ArrayAdapterActivity.start(this)
    }

    fun toSimpleAdapter(v: View) {
        SimpleAdapterActivity.start(this)
    }

    fun toBaseAdapter(v: View) {
        BaseAdapterActivity.start(this)
    }

    fun toMultiTypeAdapter(v: View) {
        LvMutilTypeActivity.start(this)
    }

    fun toCheckBoxListView(v: View) {
        CheckBoxListViewActivity.start(this)
    }

    fun toScrollViewListView(v: View) {
        ScrollViewListViewActivity.start(this)
    }

}