package br.com.missao.cleanarchitecture.app

import android.app.Application
import br.com.missao.cleanarchitecture.injections.components.AppComponent
import br.com.missao.cleanarchitecture.injections.components.DaggerAppComponent
import br.com.missao.cleanarchitecture.injections.components.DaggerViewComponent
import br.com.missao.cleanarchitecture.injections.components.ViewComponent
import br.com.missao.cleanarchitecture.injections.modules.AppModule
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Android's application class
 */
open class App : Application() {
    val instance = this

    val appModule = AppModule(instance)

    val realmInstance: Realm by lazy {
      Realm.getDefaultInstance()
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(appModule).build()
    }

    private val viewComponent: ViewComponent by lazy {
        DaggerViewComponent.builder().appModule(appModule).build()
    }

    override fun onCreate() {
        super.onCreate()
        setupRealm()
    }


    open protected fun setupRealm() {
        Realm.init(this)
        RealmConfiguration.Builder()
          .name(Realm.DEFAULT_REALM_NAME)
          .schemaVersion(0)
          .deleteRealmIfMigrationNeeded()
          .build().apply { Realm.setDefaultConfiguration(this) }
    }

    /**
     * Obtains DaggerViewComponent for injection
     */
    open fun getDaggerViewComponent() = viewComponent
}