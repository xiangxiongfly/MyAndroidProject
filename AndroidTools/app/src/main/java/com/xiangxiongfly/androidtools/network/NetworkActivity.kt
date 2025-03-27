package com.xiangxiongfly.androidtools.network

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.utils.network.NetworkHelper
import com.xiangxiongfly.common.utils.network.NetworkUtils
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
                Log.e("NETWORK", "网络是否可用：${NetworkUtils.isAvailable(context)}")
            }
            R.id.btn_network_type -> {
                Log.e("NETWORK", "网络类型：${NetworkUtils.getNetWorkTypeName(context)}")
            }
            R.id.btn_ping -> {
                thread {
                    Log.e("NETWORK", "ping：${NetworkUtils.ping()}")
                }
            }
            R.id.btn_check_wifi -> {
                Log.e("NETWORK", "wifi是否开启：${NetworkUtils.isWifiEnabled(context)}")
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
        Log.e("NETWORK", "当前无网络连接")
        tvNetwork.text = "当前无网络连接"
    }

    override fun onConnected(networkType: Int) {
        when (networkType) {
            NetworkUtils.NETWORK_WIFI -> {
                Log.e("NETWORK", "切换到wifi环境下")
                tvNetwork.text = "切换到wifi环境下"
            }
            NetworkUtils.NETWORK_2G -> {
                Log.e("NETWORK", "切换到2G环境下")
                tvNetwork.text = "切换到2G环境下"
            }
            NetworkUtils.NETWORK_3G -> {
                Log.e("NETWORK", "切换到3G环境下")
                tvNetwork.text = "切换到3G环境下"
            }
            NetworkUtils.NETWORK_4G -> {
                Log.e("NETWORK", "切换到4G环境下")
                tvNetwork.text = "切换到4G环境下"
            }
            NetworkUtils.NETWORK_5G -> {
                Log.e("NETWORK", "切换到5G环境下")
                tvNetwork.text = "切换到5G环境下"
            }
            NetworkUtils.NETWORK_UNKNOWN -> {
                Log.e("NETWORK", "未知网络")
                tvNetwork.text = "未知网络"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        NetworkHelper.unregisterListener(context, this)
    }
}