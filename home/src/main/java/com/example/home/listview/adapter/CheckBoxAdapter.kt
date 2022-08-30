package com.example.home.listview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.example.home.R
import com.example.home.bean.Fruit

class CheckBoxAdapter(val mContext: Context, val mList: ArrayList<Fruit>) : BaseAdapter() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun getCount(): Int = mList.size

    override fun getItem(position: Int): Fruit = mList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        val itemView: View
        if (convertView == null) {
            itemView = layoutInflater.inflate(R.layout.item_checkbox, null)
            val img = itemView.findViewById<ImageView>(R.id.fruit_img)
            val name = itemView.findViewById<TextView>(R.id.fruit_name)
            val checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)
            viewHolder = ViewHolder(img, name, checkBox)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = itemView.tag as ViewHolder
        }
        val item: Fruit = getItem(position)
        viewHolder.img.setImageResource(item.fruitImage)
        viewHolder.name.text = item.fruitName
        viewHolder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            item.checked = isChecked
        }
        viewHolder.checkBox.isChecked = item.checked
        return itemView
    }

    class ViewHolder(
        val img: ImageView,
        val name: TextView,
        val checkBox: CheckBox
    )
}