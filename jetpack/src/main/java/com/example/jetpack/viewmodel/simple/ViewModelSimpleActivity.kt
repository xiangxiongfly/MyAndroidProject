package com.example.jetpack.viewmodel.simple

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.jetpack.R
import com.example.jetpack.VIEWMODEL
import com.xiangxiongfly.common.base.BaseActivity


class ViewModelSimpleActivity : BaseActivity() {
    private lateinit var tvCount: TextView
    private lateinit var tvScreen: TextView

    //    private lateinit var viewModel: MyViewModel
    private val viewModel: MyViewModel by viewModels()
//    private val androidViewModel: MyAndroidViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ViewModelSimpleActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_simple)
        Log.e(VIEWMODEL, "onCreate")

        initView()
        val screenOrientation =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) "竖屏" else "横屏"
        tvScreen.text = "屏幕方向：$screenOrientation"

//        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.countLiveData.observe(this, object : Observer<Int> {
            override fun onChanged(t: Int?) {
                Log.e(VIEWMODEL, "onChanged")
                tvCount.text = t.toString()
            }
        })
    }

    private fun initView() {
        tvCount = findViewById(R.id.tv_count)
        tvScreen = findViewById(R.id.tv_screen)
    }

    fun clickIncrease(v: View) {
        viewModel.increase()
    }

    fun clickDecrease(v: View) {
        viewModel.decrease()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(VIEWMODEL, "onDestroy")
    }
}