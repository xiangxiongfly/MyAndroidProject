package com.example.core.data

import com.example.core.bean.Fruit
import com.example.mylibrary.R

object FruitData {

    fun getFruitList(count: Int = 5): ArrayList<Fruit> {
        val list = arrayListOf<Fruit>()
        for (i in 0..count) {
            val fruitImages = getFruitImages()
            val fruitNames = getFruitNames()
            for (index in fruitImages.indices) {
                list.add(Fruit(fruitImages[index], "${i}${index} ${fruitNames[index]}"))
            }
        }
        return list
    }

    fun getFruitImages(): IntArray = intArrayOf(
        R.drawable.apple_pic,
        R.drawable.banana_pic,
        R.drawable.cherry_pic,
        R.drawable.grape_pic,
        R.drawable.mango_pic,
        R.drawable.orange_pic,
        R.drawable.pear_pic,
        R.drawable.pineapple_pic,
        R.drawable.strawberry_pic,
        R.drawable.watermelon_pic,
    )


    fun getFruitNames(): Array<String> = arrayOf(
        "apple",
        "banana",
        "cherry",
        "grape",
        "mango",
        "orange",
        "pear",
        "pineapple",
        "strawberry",
        "watermelon",
    )

}