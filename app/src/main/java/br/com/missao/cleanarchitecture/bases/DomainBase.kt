package br.com.missao.cleanarchitecture.bases

import java.lang.ref.WeakReference

/**
 * Domain's Application Base
 */
open class DomainBase<T> {

    /**
     * Backing properties for [T], keeping its weak reference
     */
    private var _presenter: WeakReference<T?>? = null

    /**
     * Provides fluent get and set to [T], without boring about weak reference
     */
    var presenter: T?
        get() = _presenter?.get()
        set(value) {
            _presenter = if (value != null) WeakReference(value) else null
        }

    /**
     * Removes presenter reference
     */
    fun clear() {
        _presenter = null
    }

}