package com.github.mukiva.ticketfound.uikit

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class UiLazyDelegate<T>(
    lifecycleOwner: LifecycleOwner,
    private val valueFactory: () -> T
) : ReadOnlyProperty<LifecycleOwner, T> {

    private var mValue: T? = null

    init {
        lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                mValue = null
                owner.lifecycle.removeObserver(this)
            }
        })
    }

    override fun getValue(thisRef: LifecycleOwner, property: KProperty<*>): T = when (val value = mValue) {
        null -> valueFactory().apply {
            mValue = this
        }
        else -> value
    }
}

@MainThread
fun <T> Fragment.uiLazy(factory: () -> T) =
    UiLazyDelegate(this, factory)
