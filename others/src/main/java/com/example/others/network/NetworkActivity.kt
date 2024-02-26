package com.example.others.network

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.others.NETWORK
import com.example.others.R
import com.example.others.network.utils.NetworkHelper
import com.example.others.network.utils.NetworkUtils
import com.xiangxiongfly.common.base.BaseActivity
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
                Log.e(NETWORK, "网络是否可用：${NetworkUtils.isAvailable(mContext)}")
            }
            R.id.btn_network_type -> {
                Log.e(NETWORK, "网络类型：${NetworkUtils.getNetWorkTypeName(mContext)}")
            }
            R.id.btn_ping -> {
                thread {
                    Log.e(NETWORK, "ping：${NetworkUtils.ping()}")
                }
            }
            R.id.btn_check_wifi -> {
                Log.e(NETWORK, "wifi是否开启：${NetworkUtils.isWifiEnabled(mContext)}")
            }
            R.id.btn_network_state_changed -> {
                NetworkHelper.registerListener(mContext, this)
            }
            R.id.btn_cancel_network_state_changed -> {
                NetworkHelper.unregisterListener(mContext, this)
            }
        }
    }

    override fun onDisconnected() {
        Log.e(NETWORK, "当前无网络连接")
        tvNetwork.text = "当前无网络连接"
    }

    override fun onConnected(networkType: Int) {
        when (networkType) {
            NetworkUtils.NETWORK_WIFI -> {
                Log.e(NETWORK, "切换到wifi环境下")
                tvNetwork.text = "切换到wifi环境下"
            }
            NetworkUtils.NETWORK_2G -> {
                Log.e(NETWORK, "切换到2G环境下")
                tvNetwork.text = "切换到2G环境下"
            }
            NetworkUtils.NETWORK_3G -> {
                Log.e(NETWORK, "切换到3G环境下")
                tvNetwork.text = "切换到3G环境下"
            }
            NetworkUtils.NETWORK_4G -> {
                Log.e(NETWORK, "切换到4G环境下")
                tvNetwork.text = "切换到4G环境下"
            }
            NetworkUtils.NETWORK_5G -> {
                Log.e(NETWORK, "切换到5G环境下")
                tvNetwork.text = "切换到5G环境下"
            }
            NetworkUtils.NETWORK_UNKNOWN -> {
                Log.e(NETWORK, "未知网络")
                tvNetwork.text = "未知网络"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        NetworkHelper.unregisterListener(mContext, this)
    }
}