package com.example.home.dialog

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.base.BaseActivity
import com.example.base.utils.ToastUtils
import com.example.home.R
import java.util.*

class DialogActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
    }

    // 普通AlertDialog
    fun btn01(view: View) {
        AlertDialog.Builder(mContext)
            .setIcon(R.mipmap.ic_launcher)
            .setTitle("系统提示")
            .setMessage("这是一个普通AlertDialog")
            .setNegativeButton("取消") { dialog, which ->
                ToastUtils.show("取消")
            }
            .setPositiveButton("确定") { dialog, which ->
                ToastUtils.show("确定")
            }
            .show()
    }

    // 普通列表AlertDialog
    fun btn02(view: View) {
        val fruits = arrayOf(
            "apple",
            "banana",
            "cherry",
            "grape",
            "mango",
            "pear",
            "pineapple",
            "strawberry",
            "watermelon"
        )
        AlertDialog.Builder(mContext)
            .setTitle("水果列表")
            .setItems(fruits) { dialog, which ->
                ToastUtils.show(fruits[which])
            }
            .show()
    }

    // 单选列表AlertDialog
    fun btn03(view: View) {
        val fruits = arrayOf(
            "apple",
            "banana",
            "cherry",
            "grape",
            "mango",
            "pear",
            "pineapple",
            "strawberry",
            "watermelon"
        )
        AlertDialog.Builder(mContext)
            .setTitle("水果列表")
            .setSingleChoiceItems(fruits, 0) { dialog, which ->
                ToastUtils.show(fruits[which])
                dialog.dismiss()
            }
            .show()
    }

    // 多选列表AlertDialog
    fun btn04(view: View) {
        val fruits = arrayOf(
            "apple",
            "banana",
            "cherry",
            "grape",
            "mango",
            "pear",
            "pineapple",
            "strawberry",
            "watermelon"
        )
        val checkItems =
            booleanArrayOf(false, false, false, false, false, false, false, false, false)
        AlertDialog.Builder(mContext)
            .setTitle("水果列表")
            .setMultiChoiceItems(
                fruits, checkItems
            ) { dialog, which, isChecked -> checkItems[which] = isChecked }
            .setPositiveButton("确定") { dialog, which ->
                var ret = ""
                for (i in checkItems.indices) {
                    if (checkItems[i]) {
                        ret += fruits[i] + " "
                    }
                }
                ToastUtils.show(ret)
            }
            .show()
    }

    // 自定义AlertDialog
    fun btn05(view: View?) {
        val dialogView: View =
            LayoutInflater.from(mContext).inflate(R.layout.dialog_custom_view, null)
        val alertDialog = AlertDialog.Builder(mContext).create()
        alertDialog.apply {
            setView(dialogView)
            setCancelable(false) //按返回键是否取消
            setCanceledOnTouchOutside(false) //点击Dialog外部是否取消
        }
        dialogView.findViewById<View>(R.id.btn_cancle).setOnClickListener { alertDialog.dismiss() }
        dialogView.findViewById<View>(R.id.btn_confirm).setOnClickListener {
            ToastUtils.show("确定")
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    // 普通圆形ProgressDialog
    fun btnProgressDialog01(view: View) {
        ProgressDialog.show(mContext, "提示", "正在加载。。。", false, true)
    }

    private lateinit var progressDialog: ProgressDialog

    // 普通条形ProgressDialog
    fun btnProgressDialog02(view: View) {
        progressDialog = ProgressDialog(mContext)
        progressDialog.apply {
            setTitle("提示")
            setMessage("正在加载。。。")
            setCancelable(true)
            setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            isIndeterminate = false //是否显示进度，必须设置为false才显示
        }
        progressDialog.show()
        mHandler.sendEmptyMessageDelayed(22, 100)
    }

    val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 22) {
                progressDialog.progress = progressDialog.progress + 1
                if (progressDialog.progress == 100) {
                    progressDialog.dismiss()
                    removeCallbacksAndMessages(null)
                } else {
                    sendEmptyMessageDelayed(22, 100)
                }
            }
        }
    }

    // 日期DatePickerDialog
    fun btnDateDialog(view: View) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            mContext,
            { view, year, month, dayOfMonth ->
                var ret = ""
                ret = year.toString() + "年" + (month + 1) + "月" + dayOfMonth + "日"
                Toast.makeText(mContext, ret, Toast.LENGTH_SHORT).show()
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        ).show()
    }


    // 时间TimePickerDialog
    fun btnTimeDialog(view: View) {
        val calendar = Calendar.getInstance()
        TimePickerDialog(
            mContext, android.app.AlertDialog.THEME_HOLO_LIGHT,
            { view, hourOfDay, minute ->
                var ret = ""
                ret = hourOfDay.toString() + "时" + minute + "分"
                Toast.makeText(mContext, ret, Toast.LENGTH_SHORT).show()
            },
            calendar[Calendar.HOUR_OF_DAY],
            calendar[Calendar.MINUTE], true
        ).show()
    }

    // 自定义Dialog，仿QQ弹窗
    fun btnQQDialog(view: View) {
        val qqDialog = QQDialog(mContext)
        qqDialog.setOnDialogClickListener(object : QQDialog.OnDialogClickListener {
            override fun onVideoClick() {
                ToastUtils.show("点击了视频")
            }

            override fun onPhotoClick() {
                ToastUtils.show("点击了相册")
            }
        })
        qqDialog.show()
    }
}