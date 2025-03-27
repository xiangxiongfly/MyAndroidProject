package com.xiangxiongfly.common.utils.eventbus

data class MessageEvent<T>(val code: Int = -1, val data: T? = null)