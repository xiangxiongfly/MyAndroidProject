package com.example.jetpack.livedata.other

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.arch.core.util.Function
import androidx.lifecycle.*
import com.example.jetpack.LIVEDATA
import com.example.jetpack.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.bean.User


class LiveDataOtherActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LiveDataOtherActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_other)
    }

    override fun onResume() {
        super.onResume()
//        map()
//        switchMap()
        addSource()
    }

    fun map() {
        val userLiveData = MutableLiveData<User>()
        val mapLiveData = Transformations.map(userLiveData, object : Function<User, String> {
            override fun apply(user: User): String {
                val userString = "id:${user.id} 姓名:${user.name} 年龄:${user.age} 地址:${user.address}"
                Log.e(LIVEDATA, userString)
                return userString
            }
        })
        mapLiveData.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.e(LIVEDATA, "结果：$t")
            }
        })
        userLiveData.value = User(1, "小明", 19, "北京市")
    }

    fun switchMap() {
        val liveData1 = MutableLiveData<String>()
        val liveData2 = MutableLiveData<String>()
        val switchLiveData = MutableLiveData<Boolean>()
        val resultLiveData = Transformations.switchMap(switchLiveData,
            object : Function<Boolean, LiveData<String>> {
                override fun apply(input: Boolean): LiveData<String> {
                    if (input) {
                        return liveData1
                    } else {
                        return liveData2
                    }
                }
            })
        resultLiveData.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.e(LIVEDATA, "onChanged:$t")
            }
        })
        liveData1.value = "123"
        liveData2.value = "ABC"
        switchLiveData.value = false
    }

    fun addSource() {
        val mediatorLiveData = MediatorLiveData<String>()
        val liveData1 = MutableLiveData<String>()
        val liveData2 = MutableLiveData<String>()
        mediatorLiveData.addSource(liveData1, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.e(LIVEDATA, "onChanged liveData1:$t")
                mediatorLiveData.value = t
            }
        })
        mediatorLiveData.addSource(liveData2, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.e(LIVEDATA, "onChanged liveData2:$t")
                mediatorLiveData.value = t
            }
        })
        mediatorLiveData.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.e(LIVEDATA, "onChanged mediatorLiveData:$t")
            }
        })
//        liveData1.value="123"
        liveData2.value = "ABC"

    }
}