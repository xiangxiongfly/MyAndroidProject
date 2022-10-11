package com.example.jetpack.viewbinding.base3

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified VB : ViewBinding> Fragment.viewBindings(noinline factory: (View) -> VB) =
    FragmentViewBindingDelegate1(factory)

inline fun <reified VB : ViewBinding> Fragment.viewBindings() =
    FragmentViewBindingDelegate2(VB::class.java)

class FragmentViewBindingDelegate1<VB : ViewBinding>(
    private val factory: (View) -> VB,
) : ReadOnlyProperty<Fragment, VB> {
    private var viewBinding: VB? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        viewBinding?.let { return it }

        val lifecycle = thisRef.viewLifecycleOwner.lifecycle
        viewBinding = factory(thisRef.requireView())
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            Log.w(
                "TAG",
                "Access to viewBinding after Lifecycle is destroyed or hasn't created yet. The instance of viewBinding will be not cached."
            )
        } else {
            thisRef.viewLifecycleOwnerLiveData.observe(thisRef) { viewLifecycleOwner ->
                viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                    override fun onDestroy(owner: LifecycleOwner) {
                        viewBinding = null
                    }
                })
            }
        }

        return viewBinding!!
    }
}

class FragmentViewBindingDelegate2<VB : ViewBinding>(
    clazz: Class<VB>,
) : ReadOnlyProperty<Fragment, VB> {
    private var viewBinding: VB? = null

    private val bindMethod = clazz.getMethod("bind", View::class.java)

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        viewBinding?.let { return it }

        val lifecycle = thisRef.viewLifecycleOwner.lifecycle
        viewBinding = bindMethod.invoke(null, thisRef.requireView()) as VB
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            Log.w(
                "TAG",
                "Access to viewBinding after Lifecycle is destroyed or hasn't created yet. The instance of viewBinding will be not cached."
            )
        } else {
            thisRef.viewLifecycleOwnerLiveData.observe(thisRef) { viewLifecycleOwner ->
                viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                    override fun onDestroy(owner: LifecycleOwner) {
                        viewBinding = null
                    }
                })
            }
        }

        return viewBinding!!
    }
}