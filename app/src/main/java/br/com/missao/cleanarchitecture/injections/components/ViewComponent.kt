package br.com.missao.cleanarchitecture.injections.components

import br.com.missao.cleanarchitecture.activities.MainActivity
import br.com.missao.cleanarchitecture.injections.modules.*
import dagger.Component
import javax.inject.Singleton

/**
 * Application's view component
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RetrofitModule::class, ApiModule::class, LoggerModule::class,
        MapperModule::class, DomainModule::class, DaoModule::class, PresenterModule::class))
interface ViewComponent {

    /**
     * Injects in [MainActivity] its dependencies
     */
    fun inject(activity: MainActivity)
}