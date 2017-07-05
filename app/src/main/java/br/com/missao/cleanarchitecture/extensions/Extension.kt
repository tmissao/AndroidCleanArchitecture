package br.com.missao.cleanarchitecture.extensions

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.missao.cleanarchitecture.app.App

/**
 *  Kotlin extensions used by the application
 */

/**
 * Creates a final variable on Activity class to avoid unnecessary cast do [App]
 */
val Activity.app: App
    get() = application as App

/**
 * Inflates a view from a [ViewGroup]
 */
fun ViewGroup.inflate(layoutId: Int, attachRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachRoot)
}
