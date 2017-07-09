package br.com.missao.cleanarchitecture

import br.com.missao.cleanarchitecture.app.App
import br.com.missao.cleanarchitecture.injections.components.DaggerTestViewComponent
import br.com.missao.cleanarchitecture.injections.components.ViewComponent

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
}