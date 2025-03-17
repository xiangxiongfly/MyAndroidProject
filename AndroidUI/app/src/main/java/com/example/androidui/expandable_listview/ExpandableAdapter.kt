package com.example.androidui.expandable_listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.androidui.R
import com.example.androidui.expandable_listview.bean.Child
import com.example.androidui.expandable_listview.bean.Group

class ExpandableAdapter(
    val mContext: Context,
    private val mGroupList: ArrayList<Group>,
    private val mChildList: ArrayList<ArrayList<Child>>
) : BaseExpandableListAdapter() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun getGroupCount(): Int = mGroupList.size

    override fun getChildrenCount(groupPosition: Int): Int = mChildList[groupPosition].size

    override fun getGroup(groupPosition: Int): Group = mGroupList[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): Child =
        mChildList[groupPosition][childPosition]

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun hasStableIds(): Boolean = true

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val groupView: View
        val groupViewHolder: GroupViewHolder
        if (convertView == null) {
            groupView = layoutInflater.inflate(R.layout.layout_group, parent, false)
            val title = groupView.findViewById<TextView>(R.id.title)
            groupViewHolder = GroupViewHolder(title)
            groupView.tag = groupViewHolder
        } else {
            groupView = convertView
            groupViewHolder = groupView.tag as GroupViewHolder
        }
        val item = getGroup(groupPosition)
        groupViewHolder.title.text = item.title
        return groupView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val childView: View
        val childViewHolder: ChildViewHolder
        if (convertView == null) {
            childView = layoutInflater.inflate(R.layout.layout_child, parent, false)
            val text = childView.findViewById<TextView>(R.id.text)
            val checkBox = childView.findViewById<CheckBox>(R.id.checkBox)
            childViewHolder = ChildViewHolder(text, checkBox)
            childView.tag = childViewHolder
        } else {
            childView = convertView
            childViewHolder = childView.tag as ChildViewHolder
        }
        val item = getChild(groupPosition, childPosition)
        childViewHolder.text.text = item.text
        childViewHolder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            item.selected = isChecked
        }
        childViewHolder.checkBox.isChecked = item.selected
        return childView
    }

    class GroupViewHolder(val title: TextView)

    class ChildViewHolder(val text: TextView, val checkBox: CheckBox)
}