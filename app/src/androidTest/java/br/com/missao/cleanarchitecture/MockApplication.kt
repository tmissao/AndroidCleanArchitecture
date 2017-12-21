package br.com.missao.cleanarchitecture

import br.com.missao.cleanarchitecture.app.App
import br.com.missao.cleanarchitecture.injections.components.DaggerTestViewComponent
import br.com.missao.cleanarchitecture.injections.components.ViewComponent
import io.realm.Realm
import io.realm.RealmConfiguration



/**
 * Mock Application class for running tests
 */
class MockApplication : App() {

    /**
     * Dagger Injection Test Component [DaggerTestViewComponent]
     */
    val viewComponent = DaggerTestViewComponent.builder().build()

    /**
     *  Obtains the Dagger Injection Component, used to fool Dagger on Tests
     */
    override fun getDaggerViewComponent(): ViewComponent {
        return viewComponent
    }

    override fun setupRealm() {
        val testConfig = RealmConfiguration.Builder().inMemory().name("test-realm").build()
        val testRealm = Realm.getInstance(testConfig)
    }
}