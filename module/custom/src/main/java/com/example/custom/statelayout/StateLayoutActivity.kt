package com.example.custom.statelayout

import android.os.Bundle
import android.widget.Button
import com.example.base.BaseActivity
import com.example.base.utils.LogUtils
import com.example.custom.R
import com.example.widgets.statelayout.OnStateChangeListener
import com.example.widgets.statelayout.State
import com.example.widgets.statelayout.StateLayout

class StateLayoutActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_layout)

        val stateLayout: StateLayout = findViewById(R.id.state_layout)
        val btnLoading: Button = findViewById(R.id.btn_loading)
        val btnContent: Button = findViewById(R.id.btn_content)
        val btnError: Button = findViewById(R.id.btn_error)
        val btnEmpty: Button = findViewById(R.id.btn_empty)

        // 全局配置
//        StateConfig.emptyLayoutId = R.layout.state_empty
//        StateConfig.errorLayoutId = R.layout.state_error
//        StateConfig.loadingLayoutId = R.layout.state_loading
//        StateConfig.retryIds = intArrayOf(R.id.state_msg, R.id.state_iv)
//        StateConfig.setOnStateChangeListener(object : OnStateChangeListener {
//            override fun onStateChange(state: State) {
//                when (state) {
//                    State.LOADING -> {
//                        LogUtils.e("StateLayout", "显示加载页")
//                        postDelayed({
//                            stateLayout.showContent()
//                        }, 2000L)
//                    }
//                    State.CONTENT -> {
//                        LogUtils.e("StateLayout", "显示内容页")
//                    }
//                    State.ERROR -> {
//                        LogUtils.e("StateLayout", "显示失败页")
//                    }
//                    State.EMPTY -> {
//                        LogUtils.e("StateLayout", "显示空页")
//                    }
//                }
//            }
//        })

        // 局部配置
        stateLayout.setRetryIds(R.id.state_msg, R.id.state_iv)
        stateLayout.setOnStateChangeListener(object : OnStateChangeListener {
            override fun onStateChange(state: State) {
                when (state) {
                    State.LOADING -> {
                        LogUtils.e("StateLayout", "显示加载页")
                        postDelayed({
                            stateLayout.showContent()
                        }, 2000L)
                    }
                    State.CONTENT -> {
                        LogUtils.e("StateLayout", "显示内容页")
                    }
                    State.ERROR -> {
                        LogUtils.e("StateLayout", "显示失败页")
                    }
                    State.EMPTY -> {
                        LogUtils.e("StateLayout", "显示空页")
                    }
                }
            }
        })

        btnLoading.setOnClickListener {
            stateLayout.showLoading()
        }
        btnContent.setOnClickListener {
            stateLayout.showContent()
        }
        btnError.setOnClickListener {
            stateLayout.showError()
        }
        btnEmpty.setOnClickListener {
            stateLayout.showEmpty()
        }
    }
}