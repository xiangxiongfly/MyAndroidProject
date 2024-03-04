package com.example.jetpack.livedata.simple

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SimpleViewModel : ViewModel() {
    //创建LiveData对象
    val liveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}