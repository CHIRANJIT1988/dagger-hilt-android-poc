package com.dagger.hilt.ui.utils

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T : ViewDataBinding> FragmentActivity.dataBinding(
    @LayoutRes resId: Int
) = ActivityBindingProperty<T>(resId)

class ActivityBindingProperty<out T : ViewDataBinding>(
    @LayoutRes private val layoutIdRes: Int
) : ReadOnlyProperty<FragmentActivity, T> {

    private var binding: T? = null

    override operator fun getValue(
        thisRef: FragmentActivity,
        property: KProperty<*>
    ): T = binding ?: createBinding(thisRef).also {
        it.lifecycleOwner = thisRef
        binding = it
    }

    private fun createBinding(
        activity: FragmentActivity
    ): T = DataBindingUtil.setContentView(activity, layoutIdRes)
}
