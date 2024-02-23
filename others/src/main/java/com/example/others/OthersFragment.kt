package com.example.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.others.permissions.PermissionActivity
import com.google.android.flexbox.FlexboxLayout
import com.xiangxiongfly.common.base.BaseFragment
import com.xiangxiongfly.common.exts.addElement

class OthersFragment : BaseFragment() {
    private lateinit var flexboxLayout: FlexboxLayout

    companion object {
        @JvmStatic
        fun newInstance(title: String) = OthersFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_others, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        addElements()
    }

    private fun initViews(view: View) {
        flexboxLayout = view.findViewById(R.id.flexboxLayout)
    }

    private fun addElements() {
        flexboxLayout.removeAllViews()
        flexboxLayout.addElement(mContext, "Permissions封装", PermissionActivity::class.java)
    }

}