package com.xiangxiongfly.common.utils.okhttp.manager

import com.hjq.gson.factory.GsonFactory

class HttpParams(
    private val params: MutableMap<String, Any> = mutableMapOf()
) {
    init {
        params.putAll(HttpConfig.getParams())
    }

    // 添加
    fun addParam(key: String, value: Any) {
        params.put(key, value)
    }

    // 删除
    fun remove(key: String) {
        params.remove(key)
    }

    // 获取所有参数
    fun getParams(): MutableMap<String, Any> {
        return params // 返回不可变列表
    }

    // 清空
    fun clear() {
        params.clear()
    }

    // 转为JSON字符串
    fun toJson(): String {
        return GsonFactory.getSingletonGson().toJson(params)
    }

    // 检查是否有参数
    fun isEmpty(): Boolean {
        return params.isEmpty()
    }
}