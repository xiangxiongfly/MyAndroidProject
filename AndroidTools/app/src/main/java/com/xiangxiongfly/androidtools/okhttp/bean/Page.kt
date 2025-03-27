package com.xiangxiongfly.androidtools.okhttp.bean

data class Page(
    val curPage: Int,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int,
    val datas: ArrayList<Article>
)