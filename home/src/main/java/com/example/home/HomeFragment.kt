package com.example.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.home.viewpager.ViewPagerActivity
import com.example.home.viewpager2.ViewPager2Activity
import com.google.android.flexbox.FlexboxLayout
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.BaseFragment
import kotlin.reflect.KClass


private const val KEY_TITLE = "key_title"

class HomeFragment : BaseFragment() {
    private lateinit var flexboxLayout: FlexboxLayout

    private var mTitle: String? = null

    companion object {
        @JvmStatic
        fun newInstance(title: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TITLE, title)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mTitle = it.getString(KEY_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flexboxLayout = view.findViewById(R.id.flexboxLayout)

        flexboxLayout.removeAllViews()
        addElements()
    }

    private fun addElements() {
        addElement("ViewPager", ViewPagerActivity::class)
        addElement("ViewPager2", ViewPager2Activity::class)
    }

    private fun addElement(text: String, activityClass: KClass<out BaseActivity>) {
        val button = Button(context)
        button.text = text
        button.isAllCaps = false
        button.setOnClickListener { startActivity(Intent(context, activityClass.java)) }
        flexboxLayout.addView(button)
    }
}