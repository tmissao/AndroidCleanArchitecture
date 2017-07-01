package br.com.missao.cleanarchitecture.injections.components

import br.com.missao.cleanarchitecture.app.App
import br.com.missao.cleanarchitecture.injections.modules.AppModule
import dagger.Component
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
     * Injects [App] on App class
     */
    fun inject(app: App)
}