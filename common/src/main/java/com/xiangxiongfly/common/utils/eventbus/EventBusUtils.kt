package com.xiangxiongfly.common.utils.eventbus

import org.greenrobot.eventbus.EventBus

object EventBusUtils {
    /**
     * 注册
     */
    fun register(subscriber: Any) {
        EventBus.getDefault().register(subscriber)
    }

    /**
     * 取消注册
     */
    fun unregister(subscriber: Any) {
        EventBus.getDefault().unregister(subscriber)
    }

    /**
     * 发送普通事件
     */
    fun post(event: MessageEvent<*>) {
        EventBus.getDefault().post(event)
    }

    /**
     * 发送粘性事件
     */
    fun postSticky(event: MessageEvent<*>) {
        EventBus.getDefault().postSticky(event)
    }

    /**
     * 判断是否注册Eventbus
     */
    fun isRegister(clz: Any): Boolean {
        return clz.javaClass.isAnnotationPresent(BindEventBus::class.java)
    }
}