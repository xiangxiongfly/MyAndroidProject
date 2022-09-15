package com.example.jetpack.livedata

import android.os.Bundle
import android.view.View
import com.example.jetpack.R
import com.example.jetpack.livedata.other.LiveDataOtherActivity
import com.example.jetpack.livedata.simple.LiveDataSimpleActivity
import com.xiangxiongfly.common.base.BaseActivity

class LiveDataActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
    }

    fun toSimple(v: View) {
        LiveDataSimpleActivity.start(this)
    }

    fun toOther(v: View) {
        LiveDataOtherActivity.start(this)
    }
}