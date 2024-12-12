package com.example.tools.okhttp

import com.example.base.utils.okhttp.server.IHost

object WanUrl : IHost {
    override fun getHost(): String {
        return "https://www.wanandroid.com/"
    }

    val list = getHost() + "project/list/1/json"
    val login = getHost() + "user/login"
}