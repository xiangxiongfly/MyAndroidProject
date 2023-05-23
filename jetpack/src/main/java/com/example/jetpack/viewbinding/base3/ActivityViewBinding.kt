package com.example.jetpack.viewbinding.base3

import android.view.LayoutInflater
import androidx.core.app.ComponentActivity
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified VB : ViewBinding> ComponentActivity.viewBindings(
    noinline factory: (LayoutInflater) -> VB = { layoutInflater ->
        VB::class.java
            .getMethod("inflate", LayoutInflater::class.java)
            .invoke(null, layoutInflater) as VB
    },
    setContentView: Boolean = true
) = ActivityViewBindingDelegate(factory, setContentView)

class ActivityViewBindingDelegate<VB : ViewBinding>(
    private val factory: (LayoutInflater) -> VB,
    private val setContentView: Boolean,
) : ReadOnlyProperty<ComponentActivity, VB> {
    private var viewBinding: VB? = null

    override fun getValue(thisRef: ComponentActivity, property: KProperty<*>): VB {
        viewBinding?.let { return it }

        viewBinding = factory(thisRef.layoutInflater).also { viewBinding ->
            if (setContentView) thisRef.setContentView(viewBinding.root)
        }
        return viewBinding!!
    }
}
