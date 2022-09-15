package com.example.jetpack

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.jetpack.lifecycle.LifecycleActivity
import com.example.jetpack.livedata.LiveDataActivity
import com.example.jetpack.viewmodel.ViewModelActivity
import com.google.android.flexbox.FlexboxLayout
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.BaseFragment
import com.xiangxiongfly.common.base.KEY_TITLE
import kotlin.reflect.KClass


class JetpackFragment : BaseFragment() {
    private lateinit var flexboxLayout: FlexboxLayout

    private var mTitle: String? = null

    companion object {
        @JvmStatic
        fun newInstance(title: String) =
            JetpackFragment().apply {
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
        return inflater.inflate(R.layout.fragment_jetpack, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        addElements()
    }

    private fun initView(view: View) {
        flexboxLayout = view.findViewById(R.id.flexboxLayout)
    }

    private fun addElements() {
        flexboxLayout.removeAllViews()
        addElement("Lifecycle", LifecycleActivity::class)
        addElement("ViewModel", ViewModelActivity::class)
        addElement("LiveData", LiveDataActivity::class)
    }

    private fun addElement(title: String, activityClass: KClass<out BaseActivity>) {
        flexboxLayout.addView(
            Button(context).apply {
                text = title
                isAllCaps = false
                setOnClickListener {
                    startActivity(Intent(context, activityClass.java).apply {
                        putExtra(KEY_TITLE, title)
                    })
                }
            }
        )
    }
}