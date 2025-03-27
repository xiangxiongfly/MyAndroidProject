package com.xiangxiongfly.common.utils.okhttp

import com.xiangxiongfly.common.utils.okhttp.callback.Callback
import com.xiangxiongfly.common.utils.okhttp.manager.HttpHeaders
import com.xiangxiongfly.common.utils.okhttp.manager.HttpManager
import com.xiangxiongfly.common.utils.okhttp.manager.HttpMethod
import com.xiangxiongfly.common.utils.okhttp.manager.HttpOptions
import com.xiangxiongfly.common.utils.okhttp.manager.HttpParams
import okhttp3.Call

class HttpClient {
    private var method = HttpMethod.GET
    private lateinit var url: String
    private var params: HttpParams = HttpParams()
    private var headers: HttpHeaders = HttpHeaders()
    private var tag: Any? = null
    private var options: HttpOptions? = null

    class Builder {
        private val client = HttpClient()

        fun get(url: String): Builder {
            client.method = HttpMethod.GET
            client.url = url
            return this
        }

        fun post(url: String): Builder {
            client.method = HttpMethod.POST
            client.url = url
            return this
        }

        fun tag(tag: Any?): Builder {
            client.tag = tag
            return this
        }

        fun addHeaders(key: String, value: String): Builder {
            client.headers.addHeader(key, value)
            return this
        }

        fun addParams(key: String, value: Any): Builder {
            client.params.addParam(key, value)
            return this
        }

        fun setOptions(options: HttpOptions): Builder {
            client.options = options
            return this
        }

        fun setParams(params: HttpParams): Builder {
            client.params = params
            return this
        }

        fun executeAsync(callback: Callback<*>): Call {
            val request = HttpManager.createRequest(
                client.method,
                client.url,
                client.tag,
                client.headers,
                client.params,
                client.options
            )
            val call = HttpManager.createCall(request)
            HttpManager.executeAsync(call, callback)
            return call
        }
    }
}