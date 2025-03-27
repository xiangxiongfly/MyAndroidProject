package com.xiangxiongfly.androidtools.eventbus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.utils.eventbus.BindEventBus
import com.xiangxiongfly.common.utils.eventbus.EventBusUtils
import com.xiangxiongfly.common.utils.eventbus.MessageEvent
import com.xiangxiongfly.common.utils.eventbus.MessageCode
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@BindEventBus
class PostFragment : Fragment() {
    private lateinit var tvMessage: TextView

    companion object {
        @JvmStatic
        fun newInstance() = PostFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (EventBusUtils.isRegister(this)) {
            EventBusUtils.register(this)
        }
        tvMessage = view.findViewById(R.id.tv_message)
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

    override fun onDestroyView() {
        super.onDestroyView()
        if (EventBusUtils.isRegister(this)) {
            EventBusUtils.unregister(this)
        }
    }
}