package com.example.jetpack.viewbinding.base3

import android.view.LayoutInflater
import androidx.core.app.ComponentActivity
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified VB : ViewBinding> ComponentActivity.viewBindings(
    noinline factory: (LayoutInflater) -> VB,
    setContentView: Boolean = true
) = ActivityViewBindingDelegate1(factory, setContentView)

inline fun <reified VB : ViewBinding> ComponentActivity.viewBindings(setContentView: Boolean = true) =
    ActivityViewBindingDelegate2(VB::class.java, setContentView)

class ActivityViewBindingDelegate1<VB : ViewBinding>(
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

class ActivityViewBindingDelegate2<VB : ViewBinding>(
    private val clazz: Class<VB>,
    private val setContentView: Boolean,
) : ReadOnlyProperty<ComponentActivity, VB> {
    private var viewBinding: VB? = null

    override fun getValue(thisRef: ComponentActivity, property: KProperty<*>): VB {
        viewBinding?.let { return it }

        val inflateMethod = clazz.getMethod("inflate", LayoutInflater::class.java)
        viewBinding =
            (inflateMethod.invoke(null, thisRef.layoutInflater) as VB).also { viewBinding ->
                if (setContentView) thisRef.setContentView(viewBinding.root)
            }
        return viewBinding!!
    }
}