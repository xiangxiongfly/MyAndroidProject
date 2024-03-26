package com.example.base.utils.eventbus

class MessageEvent<T> {
    var code: Int
    var data: T? = null

    constructor(code: Int) {
        this.code = code
    }

    constructor(code: Int, data: T) {
        this.code = code
        this.data = data
    }
}