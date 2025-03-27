package com.xiangxiongfly.common.utils.okhttp.exception

object HttpCode {
    const val HTTP_ERROR = -100 // 网络请求失败
    const val NO_CONTENT_ERROR = -200 // 无内容
    const val PARSE_ERROR = -300 // 数据解析失败
    const val STATUS_ERROR = -400 // 非2XX状态码
}