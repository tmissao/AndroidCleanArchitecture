package br.com.missao.cleanarchitecture.app

import android.app.Application
import br.com.missao.cleanarchitecture.injections.components.system.AppComponent
import br.com.missao.cleanarchitecture.injections.components.system.DaggerAppComponent
import br.com.missao.cleanarchitecture.injections.modules.system.AppModule

/**
 * Android's application class
 */
class App : Application() {
    val instance = this
    val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(instance)).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}