package com.example.jetpack.viewmodel.simple

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MyAndroidViewModel(application: Application) : AndroidViewModel(application) {
    private val _countLiveData = MutableLiveData<Int>()
    val countLiveData = _countLiveData

    private var count = 0

    fun increase() {
        _countLiveData.postValue(++count)
    }

    fun decrease() {
        _countLiveData.postValue(--count)
    }
}