package br.com.missao.cleanarchitecture.injections.components

import android.content.Context
import br.com.missao.cleanarchitecture.app.App
import br.com.missao.cleanarchitecture.injections.modules.AppModule
import dagger.Component
import io.realm.Realm
import javax.inject.Singleton

/**
 * Application's component
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    /**
     * Obtains [App]
     */
    fun getApp(): App

    /**
     * Obtains [App]
     */
    fun getContext(): Context

    fun getRealm(): Realm
}