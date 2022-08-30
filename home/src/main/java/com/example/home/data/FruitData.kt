package com.example.home.data

import com.example.home.R
import com.example.home.bean.Fruit

object FruitData {

    fun getFruitList(count: Int = 30): ArrayList<Fruit> {
        val mList = arrayListOf<Fruit>()
        for (i in 0..count) {
            mList.add(Fruit(R.drawable.apple_pic, "apple$i"))
            mList.add(Fruit(R.drawable.banana_pic, "banana$i"))
            mList.add(Fruit(R.drawable.cherry_pic, "cherry$i"))
            mList.add(Fruit(R.drawable.grape_pic, "grape$i"))
            mList.add(Fruit(R.drawable.mango_pic, "mango$i"))
            mList.add(Fruit(R.drawable.orange_pic, "orange$i"))
            mList.add(Fruit(R.drawable.pear_pic, "pear$i"))
            mList.add(Fruit(R.drawable.pineapple_pic, "pineapple$i"))
            mList.add(Fruit(R.drawable.strawberry_pic, "strawberry$i"))
            mList.add(Fruit(R.drawable.watermelon_pic, "watermelon$i"))
        }
        return mList
    }

    fun getFruitImage(): IntArray =
        intArrayOf(
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


    fun getFruitName(): Array<String> =
        arrayOf<String>(
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