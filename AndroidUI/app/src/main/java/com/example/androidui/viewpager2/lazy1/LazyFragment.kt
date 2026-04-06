package com.example.androidui.viewpager2.lazy1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class LazyFragment : Fragment() {
    private var isViewInitiated = false
    private var isDataLoaded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewInitiated = true
        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isViewInitiated = false
        isDataLoaded = false
    }

    private fun loadData() {
        if (isViewInitiated && isVisible && !isDataLoaded) {
            onLazyLoad()
            isDataLoaded = true
        }
    }

    abstract fun onLazyLoad()
}