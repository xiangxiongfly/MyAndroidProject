package com.xiangxiongfly.androidtools.okhttp.url

import com.xiangxiongfly.common.utils.okhttp.server.IHost

object WanUrl : IHost {
    override fun getHost(): String {
        return "https://www.wanandroid.com/"
    }

    val list = getHost() + "project/list/1/json"
    val login = getHost() + "user/login"
}