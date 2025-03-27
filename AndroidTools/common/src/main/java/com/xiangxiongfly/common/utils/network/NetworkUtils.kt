package com.xiangxiongfly.common.utils.network

import android.Manifest
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import androidx.annotation.RequiresPermission
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * 网络工具类
 */
object NetworkUtils {
    // 无网络
    const val NETWORK_NONE = -1

    // wifi
    const val NETWORK_WIFI = 1

    // 3G
    const val NETWORK_2G = 2

    //3G
    const val NETWORK_3G = 3

    // 4G
    const val NETWORK_4G = 4

    // 5G
    const val NETWORK_5G = 5

    // 未知网络
    const val NETWORK_UNKNOWN = 6

    /**
     *  获取网络类型
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getNetWorkType(context: Context): Int {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivityManager.activeNetworkInfo // 获取当前网络状态
        if (info != null && info.isAvailable) {
            when (info.type) {
                ConnectivityManager.TYPE_WIFI -> {
                    return NETWORK_WIFI
                }
                ConnectivityManager.TYPE_MOBILE -> {
                    when (info.subtype) {
                        TelephonyManager.NETWORK_TYPE_GSM,
                        TelephonyManager.NETWORK_TYPE_GPRS,
                        TelephonyManager.NETWORK_TYPE_CDMA,
                        TelephonyManager.NETWORK_TYPE_EDGE,
                        TelephonyManager.NETWORK_TYPE_1xRTT,
                        TelephonyManager.NETWORK_TYPE_IDEN ->
                            return NETWORK_2G
                        TelephonyManager.NETWORK_TYPE_TD_SCDMA,
                        TelephonyManager.NETWORK_TYPE_EVDO_A,
                        TelephonyManager.NETWORK_TYPE_UMTS,
                        TelephonyManager.NETWORK_TYPE_EVDO_0,
                        TelephonyManager.NETWORK_TYPE_HSDPA,
                        TelephonyManager.NETWORK_TYPE_HSUPA,
                        TelephonyManager.NETWORK_TYPE_HSPA,
                        TelephonyManager.NETWORK_TYPE_EVDO_B,
                        TelephonyManager.NETWORK_TYPE_EHRPD,
                        TelephonyManager.NETWORK_TYPE_HSPAP ->
                            return NETWORK_3G
                        TelephonyManager.NETWORK_TYPE_IWLAN,
                        TelephonyManager.NETWORK_TYPE_LTE ->
                            return NETWORK_4G
                        TelephonyManager.NETWORK_TYPE_NR ->
                            return NETWORK_5G
                        else -> {
                            val subtypeName = info.subtypeName
                            return if (subtypeName.equals("TD-SCDMA", ignoreCase = true)
                                || subtypeName.equals("WCDMA", ignoreCase = true)
                                || subtypeName.equals("CDMA2000", ignoreCase = true)
                            ) {
                                NETWORK_3G
                            } else {
                                NETWORK_UNKNOWN
                            }
                        }
                    }
                }
                else -> {
                    return NETWORK_UNKNOWN
                }
            }
        } else {
            return NETWORK_NONE
        }
    }

    /**
     * 获取网络类型名
     */
    @JvmStatic
    fun getNetWorkTypeName(context: Context): String {
        return when (getNetWorkType(context)) {
            NETWORK_WIFI -> "NETWORK_WIFI"
            NETWORK_5G -> "NETWORK_5G"
            NETWORK_4G -> "NETWORK_4G"
            NETWORK_3G -> "NETWORK_3G"
            NETWORK_2G -> "NETWORK_2G"
            NETWORK_NONE -> "NETWORK_NONE"
            else -> "NETWORK_UNKNOWN"
        }
    }

    /**
     * 判断网络连接是否可用
     */
    @JvmStatic
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //如果仅仅是用来判断网络连接
        //则可以使用 cm.getActiveNetworkInfo().isAvailable();
        val info = connectivityManager.allNetworkInfo
        for (i in info.indices) {
            if (info[i].state == NetworkInfo.State.CONNECTED) {
                return true
            }
        }
        return false
    }

    /**
     * 判断网络是否可用
     * 需添加权限
     */
    @JvmStatic
//    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isAvailable(context: Context): Boolean {
        val info = getActiveNetworkInfo(context)
        return info != null && info.isAvailable
    }

    /**
     * 判断网络是否连接
     * 需添加权限
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isConnected(context: Context): Boolean {
        val info = getActiveNetworkInfo(context)
        return info != null && info.isConnected
    }

    /**
     * 判断是否有外网连接（普通方法不能判断外网的网络是否连接，比如连接上局域网）
     * 不要在主线程使用，会阻塞线程
     */
    @JvmStatic
    fun ping(): Boolean {
        var result: String? = null
        try {
            val ip = "www.baidu.com" // ping 的地址，可以换成任何一种可靠的外网
            val p = Runtime.getRuntime().exec("ping -c 3 -w 100 $ip") // ping网址3次
            // 读取ping的内容，可以不加
            val input = p.inputStream
            val `in` = BufferedReader(InputStreamReader(input))
            val stringBuffer = StringBuffer()
            var content: String? = ""
            while (`in`.readLine().also { content = it } != null) {
                stringBuffer.append(content)
            }
            Log.e("TAG", "result content : $stringBuffer");
            // ping的状态
            val status = p.waitFor()
            if (status == 0) {
                result = "success"
                return true
            } else {
                result = "failed"
            }
        } catch (e: IOException) {
            result = "IOException"
        } catch (e: InterruptedException) {
            result = "InterruptedException"
        } finally {
            Log.e("TAG", "result = $result");
        }
        return false
    }

    /**
     * ping IP
     * 不要在主线程使用，会阻塞线程
     */
    @JvmStatic
    fun ping(ip: String): Boolean {
        var result: String? = null
        try {
            // ping网址3次
            val p = Runtime.getRuntime().exec("ping -c 3 -w 100 $ip")
            // 读取ping的内容，可以不加
            val input = p.inputStream
            val `in` = BufferedReader(InputStreamReader(input))
            val stringBuffer = StringBuffer()
            var content: String? = ""
            while (`in`.readLine().also { content = it } != null) {
                stringBuffer.append(content)
            }
            Log.e("TAG", "result content : $stringBuffer");
            // ping的状态
            val status = p.waitFor()
            if (status == 0) {
                result = "success"
                return true
            } else {
                result = "failed"
            }
        } catch (e: IOException) {
            result = "IOException"
        } catch (e: InterruptedException) {
            result = "InterruptedException"
        } finally {
            Log.e("TAG", "result = $result");
        }
        return false
    }

    /**
     * 判断WIFI是否打开
     */
    @JvmStatic
//    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    fun isWifiEnabled(context: Context): Boolean {
        val wifiManager =
            context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return wifiManager.isWifiEnabled
    }

    /**
     * 判断网络连接方式是否为WIFI
     */
    @JvmStatic
    fun isWifi(context: Context): Boolean {
        val networkINfo = getActiveNetworkInfo(context)
        return networkINfo != null && networkINfo.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * 判断wifi是否连接状态
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isWifiConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * 判断网络是否是4G
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun is4G(context: Context): Boolean {
        val info = getActiveNetworkInfo(context)
        return info != null && info.isAvailable && info.subtype == TelephonyManager.NETWORK_TYPE_LTE
    }

    /**
     * 判断网络是否是5G
     */
    @JvmStatic
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun is5G(context: Context): Boolean {
        val info = getActiveNetworkInfo(context)
        return (info != null && info.isAvailable && info.subtype == TelephonyManager.NETWORK_TYPE_NR)
    }

    /**
     * 判断GPS是否打开
     */
    @JvmStatic
    fun isGpsEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val accessibleProviders = locationManager.getProviders(true)
        return accessibleProviders.size > 0
    }

    /**
     * 打开网络设置界面
     */
    @JvmStatic
    fun openWirelessSettings(context: Context) {
//        context.startActivity(Intent(Settings.ACTION_SETTINGS)) // 跳转设置界面
        context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS)) //跳转wifi设置界面
    }

    /**
     * 获取活动网络信息
     */
    private fun getActiveNetworkInfo(context: Context): NetworkInfo? {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo
    }

    /**
     * 获取移动网络运营商名称
     *
     * 如中国联通、中国移动、中国电信
     *
     * @param context 上下文
     * @return 移动网络运营商名称
     */
    @JvmStatic
    fun getNetworkOperatorName(context: Context): String? {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.networkOperatorName
    }

    /**
     * 获取移动终端类型
     *
     * @param context 上下文
     * @return 手机制式
     *
     *  * [TelephonyManager.PHONE_TYPE_NONE] : 0 手机制式未知
     *  * [TelephonyManager.PHONE_TYPE_GSM] : 1 手机制式为GSM，移动和联通
     *  * [TelephonyManager.PHONE_TYPE_CDMA] : 2 手机制式为CDMA，电信
     *  * [TelephonyManager.PHONE_TYPE_SIP] : 3
     *
     */
    @JvmStatic
    fun getPhoneType(context: Context): Int {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.phoneType
    }
}