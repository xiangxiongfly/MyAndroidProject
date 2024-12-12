package com.example.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.BaseFragment
import com.example.tools.clickarea.ClickAreaActivity
import com.example.tools.dialog.DialogActivity
import com.example.tools.eventbus.EventBusActivity
import com.example.tools.genBitmap.GenerateBitmapActivity
import com.example.tools.okhttp.OkHttpActivity
import com.example.tools.imageloader.ImageLoaderActivity
import com.example.tools.my_edittext.MyEditTextActivity
import com.example.tools.network.NetworkActivity
import com.example.tools.permissions.PermissionActivity
import com.example.tools.popupwindow.PopupWindowActivity
import com.example.tools.ratio.RatioActivity
import com.example.tools.setting_item.SettingItemActivity
import com.example.tools.signature.SignatureActivity
import com.example.tools.statelayout.StateLayoutActivity
import com.example.tools.titlebar.TitleBarActivity
import com.google.android.flexbox.FlexboxLayout
import com.xiangxiongfly.common.exts.addElement

class ToolsFragment : BaseFragment() {
    private lateinit var flexboxLayout: FlexboxLayout

    companion object {
        @JvmStatic
        fun newInstance(title: String) = ToolsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tools, container, false)
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
        flexboxLayout.addElement(mContext, "封装EventBus", EventBusActivity::class.java)
        flexboxLayout.addElement(mContext, "封装Dialog", DialogActivity::class.java)
        flexboxLayout.addElement(mContext, "封装PopupWindow", PopupWindowActivity::class.java)
        flexboxLayout.addElement(mContext, "封装Permissions", PermissionActivity::class.java)
        flexboxLayout.addElement(mContext, "封装Glide", ImageLoaderActivity::class.java)
        flexboxLayout.addElement(mContext, "封装OkHttp", OkHttpActivity::class.java)
        flexboxLayout.addElement(mContext, "监听网络状态变化", NetworkActivity::class.java)
        flexboxLayout.addElement(mContext, "SettingItem设置条", SettingItemActivity::class.java)
        flexboxLayout.addElement(mContext, "StateLayout状态页", StateLayoutActivity::class.java)
        flexboxLayout.addElement(mContext, "TitleBar标题栏", TitleBarActivity::class.java)
        flexboxLayout.addElement(mContext, "自定义EditText", MyEditTextActivity::class.java)
        flexboxLayout.addElement(mContext, "保持宽高比例", RatioActivity::class.java)
        flexboxLayout.addElement(mContext, "将View生成Bitmap", GenerateBitmapActivity::class.java)
        flexboxLayout.addElement(mContext, "扩大View的点击区域", ClickAreaActivity::class.java)
        flexboxLayout.addElement(mContext, "手写签名板", SignatureActivity::class.java)
    }

}