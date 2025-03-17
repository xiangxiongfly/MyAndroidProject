package com.example.core.data

import com.example.core.bean.Fruit
import com.example.mylibrary.R

object FruitData {

    fun getFruitList(count: Int = 5): ArrayList<Fruit> {
        val mList = arrayListOf<Fruit>()
        for (i in 0..count) {
            mList.add(Fruit(R.drawable.apple_pic, "苹果$i"))
            mList.add(Fruit(R.drawable.banana_pic, "香蕉$i"))
            mList.add(Fruit(R.drawable.cherry_pic, "樱桃$i"))
            mList.add(Fruit(R.drawable.grape_pic, "葡萄$i"))
            mList.add(Fruit(R.drawable.mango_pic, "芒果$i"))
            mList.add(Fruit(R.drawable.orange_pic, "橘子$i"))
            mList.add(Fruit(R.drawable.pear_pic, "例子$i"))
            mList.add(Fruit(R.drawable.pineapple_pic, "菠萝$i"))
            mList.add(Fruit(R.drawable.strawberry_pic, "草莓$i"))
            mList.add(Fruit(R.drawable.watermelon_pic, "西瓜$i"))
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