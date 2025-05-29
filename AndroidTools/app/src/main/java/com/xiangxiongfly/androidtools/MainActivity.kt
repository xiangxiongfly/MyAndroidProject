package com.xiangxiongfly.androidtools

import android.os.Bundle
import com.google.android.flexbox.FlexboxLayout
import com.xiangxiongfly.androidtools.clickarea.ClickAreaActivity
import com.xiangxiongfly.androidtools.countdown.CountDownActivity
import com.xiangxiongfly.androidtools.debouncethrottle.DebounceThrottleActivity
import com.xiangxiongfly.androidtools.dialog.DialogActivity
import com.xiangxiongfly.androidtools.eventbus.EventBusActivity
import com.xiangxiongfly.androidtools.genBitmap.GenerateBitmapActivity
import com.xiangxiongfly.androidtools.imageloader.ImageLoaderActivity
import com.xiangxiongfly.androidtools.measuretext.MeasureTextActivity
import com.xiangxiongfly.androidtools.network.NetworkActivity
import com.xiangxiongfly.androidtools.okhttp.OkHttpActivity
import com.xiangxiongfly.androidtools.permissions.PermissionActivity
import com.xiangxiongfly.androidtools.popupwindow.PopupWindowActivity
import com.xiangxiongfly.androidtools.ratio.RatioActivity
import com.xiangxiongfly.androidtools.signature.SignatureActivity
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.exts.addElement

class MainActivity : BaseActivity() {
    private lateinit var flexboxLayout: FlexboxLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        addElements()
    }

    private fun initViews() {
        flexboxLayout = findViewById(R.id.flexboxLayout)
    }

    private fun addElements() {
        flexboxLayout.removeAllViews()
        flexboxLayout.addElement(context, "封装EventBus", EventBusActivity::class.java)
        flexboxLayout.addElement(context, "封装Dialog", DialogActivity::class.java)
        flexboxLayout.addElement(context, "封装PopupWindow", PopupWindowActivity::class.java)
        flexboxLayout.addElement(context, "封装Permissions", PermissionActivity::class.java)
        flexboxLayout.addElement(context, "封装Glide", ImageLoaderActivity::class.java)
        flexboxLayout.addElement(context, "封装OkHttp", OkHttpActivity::class.java)
        flexboxLayout.addElement(context, "监听网络状态变化", NetworkActivity::class.java)
        flexboxLayout.addElement(context, "保持宽高比例", RatioActivity::class.java)
        flexboxLayout.addElement(context, "将View生成Bitmap", GenerateBitmapActivity::class.java)
        flexboxLayout.addElement(context, "扩大View的点击区域", ClickAreaActivity::class.java)
        flexboxLayout.addElement(context, "手写签名板", SignatureActivity::class.java)
        flexboxLayout.addElement(context, "测量文本宽高总结", MeasureTextActivity::class.java)
        flexboxLayout.addElement(context, "防抖和节流", DebounceThrottleActivity::class.java)
        flexboxLayout.addElement(context, "倒计时总结", CountDownActivity::class.java)
    }
}