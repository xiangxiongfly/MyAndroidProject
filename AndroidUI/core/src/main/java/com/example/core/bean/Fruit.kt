package com.example.core.bean

data class Fruit(
    var fruitImage: Int,
    var fruitName: String,
    var count: Int = 0
) {
    var checked: Boolean = false
}
