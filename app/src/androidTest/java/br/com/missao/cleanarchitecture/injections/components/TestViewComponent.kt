package br.com.missao.cleanarchitecture.injections.components

import br.com.missao.cleanarchitecture.activities.MainActivityTest
import br.com.missao.cleanarchitecture.injections.modules.TestLoggerModule
import br.com.missao.cleanarchitecture.injections.modules.TestPresenterModule
import dagger.Component
import javax.inject.Singleton

/**
 * Application's view component
 */
@Singleton
@Component(modules = arrayOf(TestLoggerModule::class, TestPresenterModule::class))
interface TestViewComponent : ViewComponent {

    fun inject(activity: MainActivityTest)
}