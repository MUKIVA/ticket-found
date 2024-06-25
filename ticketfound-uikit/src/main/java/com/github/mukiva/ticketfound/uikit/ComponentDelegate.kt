package com.github.mukiva.ticketfound.uikit

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class Component {

    abstract fun initComponent()

    interface IStateObserverComponent<VM : ViewModel> {
        fun subscribeOnViewModel(viewModel: VM, owner: LifecycleOwner)
    }

}

class ComponentDelegate<C : Component>(
    private val fragment: Fragment,
    private val componentFactory: () -> C
) : ReadOnlyProperty<Fragment, C> {

    private var mValue: C? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            val viewLifecycleOwnerLiveDataObserver =
                Observer<LifecycleOwner?> {
                    val viewLifecycleOwner = it ?: return@Observer

                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            mValue = null
                        }
                    })
                }

            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observeForever(viewLifecycleOwnerLiveDataObserver)
            }

            override fun onDestroy(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.removeObserver(viewLifecycleOwnerLiveDataObserver)
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): C {
        val value = mValue
        if (value != null) {
            return value
        }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Should not attempt to get component when Fragment views are destroyed.")
        }

        return componentFactory().also { mValue = it }
    }
}

@MainThread
fun <C : Component> Fragment.component(factory: () -> C) = ComponentDelegate<C>(
    this, factory
)