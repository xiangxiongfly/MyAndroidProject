package com.xiangxiongfly.androidtools.debouncethrottle

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.utils.DebounceThrottleUtils
import com.xiangxiongfly.common.utils.clickDebounce
import com.xiangxiongfly.common.utils.clickThrottle
import com.xiangxiongfly.common.utils.textChangeDebounce

class DebounceThrottleActivity : BaseActivity() {
    private lateinit var etSearch: EditText
    private lateinit var btnSubmit: Button
    private lateinit var btnSubmit2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debounce_throttle)
        etSearch = findViewById<EditText>(R.id.et_search)
        btnSubmit = findViewById<Button>(R.id.btn_submit_debounce)
        btnSubmit2 = findViewById<Button>(R.id.btn_submit_throttle)

        etSearch.textChangeDebounce(debounceTime = 1000L) {
            Log.e("TAG", "textChange:${it.toString()}")
        }
        btnSubmit.clickDebounce(debounceTime = 1000L) {
            Log.e("TAG", "click-debounce-${System.currentTimeMillis()}")
        }
        btnSubmit2.clickThrottle(throttleTime = 1000L) {
            Log.e("TAG", "click-throttle-${System.currentTimeMillis()}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DebounceThrottleUtils.clearAll()
    }
}