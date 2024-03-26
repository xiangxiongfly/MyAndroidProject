package com.example.home.listview

import android.os.Bundle
import android.view.View
import com.example.base.BaseActivity
import com.example.home.R

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