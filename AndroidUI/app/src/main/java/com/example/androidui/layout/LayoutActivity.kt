package com.example.androidui.layout

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.androidui.R
import com.example.core.base.BaseActivity

class LayoutActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_linearlayout01)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_layout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.linearLayout01 -> {
                setContentView(R.layout.layout_linearlayout01)
            }
            R.id.linearLayout02 -> {
                setContentView(R.layout.layout_linearlayout02)
            }
            R.id.relativeLayout01 -> {
                setContentView(R.layout.layout_relativelayout01)
            }
            R.id.relativeLayout02 -> {
                setContentView(R.layout.layout_relativelayout02)
            }
            R.id.frameLayout01 -> {
                setContentView(R.layout.layout_framelayout01)
            }
            R.id.gridLayout01 -> {
                setContentView(R.layout.layout_gridlayout01)
            }
            R.id.gridLayout02 -> {
                setContentView(R.layout.layout_gridlayout02)
            }
        }
        return true
    }
}