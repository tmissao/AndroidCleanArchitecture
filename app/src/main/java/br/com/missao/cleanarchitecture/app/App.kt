package br.com.missao.cleanarchitecture.app

import android.app.Application
import br.com.missao.cleanarchitecture.injections.components.AppComponent
import br.com.missao.cleanarchitecture.injections.components.DaggerAppComponent
import br.com.missao.cleanarchitecture.injections.components.DaggerViewComponent
import br.com.missao.cleanarchitecture.injections.modules.AppModule

/**
 * Android's application class
 */
open class App : Application() {
    val instance = this
    val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(instance)).build()
    }

    private val viewComponent = DaggerViewComponent.builder().build()

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    /**
     * Obtains DaggerViewComponent for injection
     */
    open fun getDaggerViewComponent()
            = viewComponent

}