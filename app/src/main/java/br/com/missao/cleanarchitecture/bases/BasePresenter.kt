package br.com.missao.cleanarchitecture.bases

import java.lang.ref.WeakReference

/**
 * Presenter's Application Base
 */
open class BasePresenter<V, D> {

    /**
     * Backing properties for [V], keeping its weak reference
     */
    private var _view: WeakReference<V?>? = null

    /**
     * Provides fluent get and set to [V], without boring about weak reference
     */
    var view: V?
        get() = _view?.get()
        set(value) {
            _view = if (value != null) WeakReference(value) else null
        }

    /**
     * Domain
     */
    var domain: D? = null

    /**
     * Removes presenter and domain references
     */
    fun clear() {
        domain = null
        _view = null
    }
}