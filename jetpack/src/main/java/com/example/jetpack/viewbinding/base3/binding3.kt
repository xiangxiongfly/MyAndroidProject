package com.example.jetpack.viewbinding.base3


/**
 * Fragment 绑定委托，可以从 onViewCreated 到 onDestroyView（含）使用
 */
//fun <VB : ViewBinding> Fragment.viewBinding(factory: (View) -> VB): ReadOnlyProperty<Fragment, VB> =
//    object : ReadOnlyProperty<Fragment, VB>, DefaultLifecycleObserver {
//        private var binding: VB? = null
//
//        override fun getValue(thisRef: Fragment, property: KProperty<*>): VB =
//            binding ?: factory(requireView()).also {
//                // 如果在 Lifecycle 被销毁后访问绑定，则创建新实例，但不要缓存它
//                if (viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
//                    viewLifecycleOwner.lifecycle.addObserver(this)
//                    binding = it
//                }
//            }
//
//        override fun onDestroy(owner: LifecycleOwner) {
//            binding = null
//        }
//    }
//
//inline fun <reified VB : ViewBinding> Fragment.viewBinding() = FragmentBindProperty(VB::class.java)
//
//class FragmentBindProperty<VB : ViewBinding>(private val clazz: Class<VB>) :
//    ReadOnlyProperty<Fragment, VB> {
//
//    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
//        return requireNotNull(thisRef.view) { "The constructor missing layout id or the property of ${property.name} has been destroyed." }
//            .getViewBinding(clazz)
//    }
//}
//
//inline fun <reified VB : ViewBinding> View.getViewBinding() = getViewBinding(VB::class.java)
//
//fun <VB : ViewBinding> View.getViewBinding(clazz: Class<VB>) =
//    getTag(R.id.tag_view_binding) as? VB ?: (clazz.getMethod("bind", View::class.java)
//        .invoke(null, this) as VB)
//        .also { setTag(R.id.tag_view_binding, it) }
