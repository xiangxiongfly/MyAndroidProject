package com.example.jetpack.livedata.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.jetpack.LIVEDATA
import com.example.jetpack.R
import com.xiangxiongfly.common.base.BaseActivity

class LiveDataSimpleActivity : BaseActivity() {
    private val viewModel: SimpleViewModel by viewModels()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LiveDataSimpleActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_simple)
        val name: TextView = findViewById(R.id.name)

        //创建观察者对象
        val observer = object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.e(LIVEDATA, "onChanged: $t")
                name.text = t
            }
        }

        //观察LiveData对象
        viewModel.liveData.observe(this, observer)

        //observeForever
//        viewModel.liveData.observeForever(observer)

//        viewModel.liveData.setValue("onCreate") //非活跃状态，不会通知观察者
    }

    fun sendMessage(v: View) {
//        viewModel.liveData.setValue("hello world")
        viewModel.liveData.postValue("hello world")
    }

    override fun onStart() {
        super.onStart()
//        viewModel.liveData.setValue("onStart") //活跃状态，会通知观察者
    }

    override fun onResume() {
        super.onResume()
        viewModel.liveData.setValue("onResume") //活跃状态，会通知观察者

//        Log.e(LIVEDATA,"onResume 是否有观察者：${viewModel.liveData.hasObservers()}")
    }

    override fun onPause() {
        super.onPause()
        viewModel.liveData.setValue("onPause") //活跃状态，会通知观察者
    }

    override fun onStop() {
        super.onStop()
        viewModel.liveData.setValue("onStop") //非活跃状态，不会通知观察者
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.liveData.setValue("onDestroy") //非活跃状态，不会通知观察者
    }
}