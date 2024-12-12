package com.example.base.utils.okhttp.manager

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.base.utils.okhttp.callback.Callback
import com.example.base.utils.okhttp.exception.HttpCode
import com.example.base.utils.okhttp.exception.HttpException
import com.hjq.gson.factory.GsonFactory
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.net.UnknownHostException

object HttpManager {

    private val MAIN_HANDLER = Handler(Looper.getMainLooper())

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 日志拦截器
        })
        .build()

    // 创建请求对象
    fun createRequest(
        method: HttpMethod,
        url: String,
        tag: Any?,
        headers: HttpHeaders?,
        params: HttpParams,
        options: HttpOptions?
    ): Request {
        val requestBuilder = Request.Builder()
        requestBuilder.url(url)
        tag?.let {
            requestBuilder.tag(it)
        }
        headers?.let {
            addRequestHeaders(requestBuilder, it)
        }
        addRequestParams(method, requestBuilder, params, options)
        return requestBuilder.build()
    }

    // 创建连接对象
    fun createCall(request: Request): Call {
        return client.newCall(request)
    }

    // 添加请求头
    private fun addRequestHeaders(requestBuilder: Request.Builder, headers: HttpHeaders) {
        if (!headers.isEmpty()) {
            for ((key, value) in headers.getHeaders()) {
                requestBuilder.addHeader(key, value)
            }
        }
    }

    // 添加请求参数
    private fun addRequestParams(
        method: HttpMethod,
        requestBuilder: Request.Builder,
        params: HttpParams,
        options: HttpOptions?
    ) {
        when (method) {
            HttpMethod.GET -> {
                handleGetRequest(requestBuilder, params)
            }
            HttpMethod.POST -> {
                handlePostRequest(requestBuilder, params, options)
            }
        }
    }

    private fun handleGetRequest(requestBuilder: Request.Builder, params: HttpParams) {
        val urlBuilder = requestBuilder.build().url.newBuilder()
        if (!params.isEmpty()) {
            for ((key, value) in params.getParams()) {
                urlBuilder.addQueryParameter(key, value.toString())
            }
        }
        val url = urlBuilder.build()
        requestBuilder.url(url)
        requestBuilder.method("GET", null)
    }

    private fun handlePostRequest(
        requestBuilder: Request.Builder,
        params: HttpParams,
        options: HttpOptions?
    ) {
        if (options != null && options.isJson) {
            val json = GsonFactory.getSingletonGson().toJson(params.getParams())
            Log.e("TAG", "Json: $json")
            val toRequestBody = json.toRequestBody("application/json; charset=utf-8".toMediaType())
            requestBuilder.method("POST", toRequestBody)
        } else {
            val formBodyBuilder = FormBody.Builder()
            if (!params.isEmpty()) {
                for ((key, value) in params.getParams()) {
                    formBodyBuilder.add(key, value.toString())
                }
            }
            val requestBody = formBodyBuilder.build()
            requestBuilder.method("POST", requestBody)
        }
    }

    fun <T> executeAsync(call: Call, callback: Callback<T>) {
        callback.onStart()
        call.enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                when (e) {
                    is UnknownHostException ->
                        handleCallbackError(
                            callback,
                            HttpCode.HTTP_ERROR,
                            "服务器连接异常，请稍后再试",
                            e
                        )
                    is IOException ->
                        handleCallbackError(
                            callback,
                            HttpCode.HTTP_ERROR,
                            "请求被中断，请重试",
                            e
                        )
                    else ->
                        handleCallbackError(
                            callback,
                            HttpCode.HTTP_ERROR,
                            "网络连接异常,请稍后重试",
                            e
                        )
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    if (response.body != null) {
                        val text = response.body!!.string()
                        val type = getGenericType(callback)
                        try {
                            val result = GsonFactory.getSingletonGson().fromJson<T>(text, type)
                            handleCallbackSuccess(callback, result)
                        } catch (e: Exception) {
                            handleCallbackError(
                                callback,
                                HttpCode.PARSE_ERROR,
                                "数据解析异常，请稍后再试",
                                null
                            )
                        }
                    } else {
                        handleCallbackError(
                            callback,
                            HttpCode.NO_CONTENT_ERROR,
                            "服务器数据返回异常，请稍后再试",
                            null
                        )
                    }
                } else {
                    handleCallbackError(callback, response.code, response.message, null)
                }
            }
        })
    }

    private fun <T> handleCallbackSuccess(callback: Callback<T>, result: T) {
        MAIN_HANDLER.post {
            callback.onSuccess(result)
            callback.onComplete()
        }
    }

    private fun <T> handleCallbackError(
        callback: Callback<T>,
        code: Int,
        message: String,
        e: Exception?
    ) {
        MAIN_HANDLER.post {
            callback.onError(HttpException(code, message, e))
            callback.onComplete()
        }
    }

    private fun getGenericType(obj: Any): Type? {
        // 获取接口上的泛型
        val genericInterfaces = obj.javaClass.genericInterfaces
        if (genericInterfaces.isNotEmpty()) {
            for (type in genericInterfaces) {
                if (type is ParameterizedType) {
                    return type.actualTypeArguments[0]
                }
            }
        }
        // 获取父类上的泛型
        val genericSuperclass = obj.javaClass.genericSuperclass
        if (genericSuperclass is ParameterizedType) {
            val parameterizedType = genericSuperclass as ParameterizedType
            val actualTypeArguments = parameterizedType.actualTypeArguments
            if (actualTypeArguments.isNotEmpty()) {
                return actualTypeArguments[0]
            }
        }
        return Void::class.java
    }

}