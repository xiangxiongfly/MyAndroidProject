package com.example.jetpack.viewmodel.simple

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
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