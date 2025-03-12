package com.example.tools.network

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.base.BaseActivity
import com.example.base.utils.LogUtils
import com.example.tools.R
import com.example.base.utils.network.NetworkHelper
import com.example.base.utils.network.NetworkUtils
import kotlin.concurrent.thread

class NetworkActivity : BaseActivity(), NetworkHelper.OnNetworkStateChangedListener {
    private lateinit var tvNetwork: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)
        initViews()
    }

    private fun initViews() {
        tvNetwork = findViewById(R.id.tv_network)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_network_enable -> {
                LogUtils.e("NETWORK", "网络是否可用：${NetworkUtils.isAvailable(context)}")
            }
            R.id.btn_network_type -> {
                LogUtils.e("NETWORK", "网络类型：${NetworkUtils.getNetWorkTypeName(context)}")
            }
            R.id.btn_ping -> {
                thread {
                    LogUtils.e("NETWORK", "ping：${NetworkUtils.ping()}")
                }
            }
            R.id.btn_check_wifi -> {
                LogUtils.e("NETWORK", "wifi是否开启：${NetworkUtils.isWifiEnabled(context)}")
            }
            R.id.btn_network_state_changed -> {
                NetworkHelper.registerListener(context, this)
            }
            R.id.btn_cancel_network_state_changed -> {
                NetworkHelper.unregisterListener(context, this)
            }
        }
    }

    override fun onDisconnected() {
        LogUtils.e("NETWORK", "当前无网络连接")
        tvNetwork.text = "当前无网络连接"
    }

    override fun onConnected(networkType: Int) {
        when (networkType) {
            NetworkUtils.NETWORK_WIFI -> {
                LogUtils.e("NETWORK", "切换到wifi环境下")
                tvNetwork.text = "切换到wifi环境下"
            }
            NetworkUtils.NETWORK_2G -> {
                LogUtils.e("NETWORK", "切换到2G环境下")
                tvNetwork.text = "切换到2G环境下"
            }
            NetworkUtils.NETWORK_3G -> {
                LogUtils.e("NETWORK", "切换到3G环境下")
                tvNetwork.text = "切换到3G环境下"
            }
            NetworkUtils.NETWORK_4G -> {
                LogUtils.e("NETWORK", "切换到4G环境下")
                tvNetwork.text = "切换到4G环境下"
            }
            NetworkUtils.NETWORK_5G -> {
                LogUtils.e("NETWORK", "切换到5G环境下")
                tvNetwork.text = "切换到5G环境下"
            }
            NetworkUtils.NETWORK_UNKNOWN -> {
                LogUtils.e("NETWORK", "未知网络")
                tvNetwork.text = "未知网络"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        NetworkHelper.unregisterListener(context, this)
    }
}