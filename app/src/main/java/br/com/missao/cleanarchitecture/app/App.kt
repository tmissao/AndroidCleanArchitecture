package br.com.missao.cleanarchitecture.app

import android.app.Application
import br.com.missao.cleanarchitecture.injections.components.AppComponent
import br.com.missao.cleanarchitecture.injections.components.DaggerAppComponent
import br.com.missao.cleanarchitecture.injections.components.DaggerViewComponent
import br.com.missao.cleanarchitecture.injections.components.ViewComponent
import br.com.missao.cleanarchitecture.injections.modules.AppModule

/**
 * Android's application class
 */
open class App : Application() {
    val instance = this

    val appModule = AppModule(instance)

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(appModule).build()
    }


    private val viewComponent: ViewComponent by lazy {
        DaggerViewComponent.builder().appModule(appModule).build()
    }

    override fun onCreate() {
        super.onCreate()
    }

    /**
     * Obtains DaggerViewComponent for injection
     */
    open fun getDaggerViewComponent()
            = viewComponent

}