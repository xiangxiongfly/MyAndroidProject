package com.xiangxiongfly.androidtools.eventbus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.utils.eventbus.EventBusUtils
import com.xiangxiongfly.common.utils.eventbus.MessageEvent
import com.xiangxiongfly.common.utils.eventbus.MessageCode

class PostActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, PostActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
    }

    fun refreshClick(view: View) {
        EventBusUtils.post(MessageEvent<Nothing>(MessageCode.REFRESH))
    }

    fun deleteClick(view: View) {
        EventBusUtils.post(MessageEvent<Nothing>(MessageCode.DELETE))
    }

    fun addClick(view: View) {
        EventBusUtils.post(MessageEvent(MessageCode.ADD, "hello world"))
    }
}