package br.com.missao.cleanarchitecture.injections.components.system

import br.com.missao.cleanarchitecture.activities.MainActivity
import br.com.missao.cleanarchitecture.injections.modules.system.*
import dagger.Component
import javax.inject.Singleton

/**
 * Application's view component
 */
@Singleton
@Component(modules = arrayOf(RetrofitModule::class, ApiModule::class, LoggerModule::class,
        DomainModule::class, PresenterModule::class))
interface ViewComponent {

    fun inject(activity: MainActivity)
}