package com.xiangxiongfly.common.popupwindow

import android.util.SparseArray
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes

/**
 * 处理控件
 */
class ViewHolder(private val convertView: View) {

    companion object {
        @JvmStatic
        fun create(view: View): ViewHolder {
            return ViewHolder(view)
        }
    }

    private val views = SparseArray<View>()

    /**
     * 获取控件
     */
    fun <T : View> getView(@IdRes viewId: Int): T {
        var view = views.get(viewId)
        if (view == null) {
            view = convertView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T
    }

    /**
     * 设置文本
     */
    fun setText(@IdRes viewId: Int, text: String) {
        val textView = getView<TextView>(viewId)
        textView.text = text
    }

    /**
     * 设置文本颜色
     */
    fun setTextColor(@IdRes viewId: Int, @ColorInt color: Int) {
        val textView = getView<TextView>(viewId)
        textView.setTextColor(color)
    }

    /**
     * 设置控件的背景图片
     */
    fun setBackgroundResource(@IdRes viewId: Int, @DrawableRes resId: Int) {
        val view = getView<View>(viewId)
        view.setBackgroundResource(resId)
    }

    /**
     * 设置控件的背景颜色
     */
    fun setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int) {
        val view = getView<View>(viewId)
        view.setBackgroundColor(color)
    }

    /**
     * 设置点击事件
     */
    fun setOnClickListener(@IdRes viewId: Int, listener: View.OnClickListener) {
        val view = getView<View>(viewId)
        view.setOnClickListener(listener)
    }
}