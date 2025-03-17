package com.example.androidui.listview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.androidui.R
import com.example.common.bean.LeftFruit
import com.example.common.bean.RightFruit

class MutilTypeAdapter(val mContext: Context, val mDatas: ArrayList<Any>) : BaseAdapter() {
    companion object {
        const val TYPE_LEFT = 0
        const val TYPE_RIGHT = 1
    }

    private val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun getCount(): Int = mDatas.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItem(position: Int): Any {
        return mDatas[position]
    }

    override fun getViewTypeCount(): Int {
        return 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (mDatas[position] is LeftFruit) {
            TYPE_LEFT
        } else {
            TYPE_RIGHT
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var leftViewHolder: LeftViewHolder? = null
        var rightViewHolder: RightViewHolder? = null
        val itemView: View
        val type: Int = getItemViewType(position)

        if (convertView == null) {
            when (type) {
                TYPE_LEFT -> {
                    itemView = layoutInflater.inflate(R.layout.item_left_fruit, null)
                    val img: ImageView = itemView.findViewById(R.id.fruit_img)
                    val name: TextView = itemView.findViewById(R.id.fruit_name)
                    leftViewHolder = LeftViewHolder(name, img)
                    itemView.setTag(R.id.type_left, leftViewHolder)
                }
                else -> {
                    itemView = layoutInflater.inflate(R.layout.item_right_fruit, null)
                    val img: ImageView = itemView.findViewById(R.id.fruit_img)
                    val name: TextView = itemView.findViewById(R.id.fruit_name)
                    rightViewHolder = RightViewHolder(name, img)
                    itemView.setTag(R.id.type_right, rightViewHolder)
                }
            }
        } else {
            itemView = convertView
            when (type) {
                TYPE_LEFT -> {
                    leftViewHolder = itemView.getTag(R.id.type_left) as LeftViewHolder
                }
                else -> {
                    rightViewHolder = itemView.getTag(R.id.type_right) as RightViewHolder
                }
            }
        }

        val item: Any = getItem(position)
        when (type) {
            TYPE_LEFT -> {
                val leftFruit: LeftFruit = item as LeftFruit
                leftViewHolder!!.img.setImageResource(leftFruit.fruitImage)
                leftViewHolder.name.text = leftFruit.fruitName
            }
            TYPE_RIGHT -> {
                val rightFruit: RightFruit = item as RightFruit
                rightViewHolder!!.img.setImageResource(rightFruit.fruitImage)
                rightViewHolder.name.text = rightFruit.fruitName
            }
        }

        return itemView
    }

    class LeftViewHolder(
        val name: TextView,
        val img: ImageView
    )

    class RightViewHolder(
        val name: TextView,
        val img: ImageView
    )
}