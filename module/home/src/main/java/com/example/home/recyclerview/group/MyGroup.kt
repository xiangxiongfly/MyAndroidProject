package com.example.home.recyclerview.group

import androidx.annotation.DrawableRes

data class MyGroup(val groupName: String, val list: MutableList<MyItem>)

data class MyItem(val itemName: String, @DrawableRes val itemImg: Int)