package com.xiangxiongfly.androidtools.eventbus

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.utils.eventbus.BindEventBus
import com.xiangxiongfly.common.utils.eventbus.EventBusUtils
import com.xiangxiongfly.common.utils.eventbus.MessageEvent
import com.xiangxiongfly.common.utils.eventbus.MessageCode
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import com.xiangxiongfly.common.base.BaseActivity

@BindEventBus
class EventBusActivity : BaseActivity() {
    private lateinit var tvMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus)
        if (EventBusUtils.isRegister(this)) {
            EventBusUtils.register(this)
        }
        tvMessage = findViewById(R.id.tv_message)
    }

    fun toPostActivity(view: View) {
        PostActivity.Companion.actionStart(context)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent<*>) {
        when (event.code) {
            MessageCode.REFRESH -> {
                tvMessage.text = "刷新数据"
            }

            MessageCode.DELETE -> {
                tvMessage.text = "删除数据"
            }

            MessageCode.ADD -> {
                tvMessage.text = "添加数据：${event.data}"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBusUtils.isRegister(this)) {
            EventBusUtils.unregister(this)
        }
    }
}