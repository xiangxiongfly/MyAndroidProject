package com.example.tools.eventbus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.base.BaseActivity
import com.example.base.utils.eventbus.EventBusUtils
import com.example.base.utils.eventbus.MessageEvent
import com.example.base.utils.eventbus.MessageEventCode
import com.example.tools.R

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
        EventBusUtils.post(MessageEvent<Nothing>(MessageEventCode.REFRESH))
    }

    fun deleteClick(view: View) {
        EventBusUtils.post(MessageEvent<Nothing>(MessageEventCode.DELETE))
    }

    fun addClick(view: View) {
        EventBusUtils.post(MessageEvent(MessageEventCode.ADD, "hello world"))
    }
}