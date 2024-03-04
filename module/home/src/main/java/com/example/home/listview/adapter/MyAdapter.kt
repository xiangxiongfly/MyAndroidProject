package com.example.home.listview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.home.R
import com.xiangxiongfly.common.bean.Fruit

class MyAdapter(val mContext: Context, val mDatas: ArrayList<Fruit>) : BaseAdapter() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun getCount(): Int = mDatas.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItem(position: Int): Fruit = mDatas[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        val itemView: View
        if (convertView == null) {
            itemView = layoutInflater.inflate(R.layout.item_fruit, null)
            val fruitImage: ImageView = itemView.findViewById(R.id.fruit_img)
            val fruitName: TextView = itemView.findViewById(R.id.fruit_name)
            viewHolder = ViewHolder(fruitImage, fruitName)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = itemView.tag as ViewHolder
        }
        val fruit = getItem(position)
        viewHolder.fruitImage.setImageResource(fruit.fruitImage)
        viewHolder.fruitName.text = fruit.fruitName
        return itemView
    }

    /**
     * 局部刷新
     */
    fun changeItem(listView: ListView, position: Int) {
        val startPosition: Int = listView.headerViewsCount
        val endPosition: Int = listView.count - listView.footerViewsCount
        val firstVisiblePosition: Int = listView.firstVisiblePosition
        val lastVisiblePosition: Int = listView.lastVisiblePosition
        if (position >= startPosition && position < endPosition) {
            val realPosition = position - listView.headerViewsCount
            mDatas[realPosition].fruitName = "${mDatas[realPosition].fruitName} 被吃掉了"
            if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
                val childView = listView.getChildAt(position - firstVisiblePosition)
                getView(realPosition, childView, listView)
            }
            Toast.makeText(mContext, mDatas[realPosition].fruitName, Toast.LENGTH_SHORT).show()
        }
    }

    class ViewHolder(
        val fruitImage: ImageView,
        val fruitName: TextView
    )
}