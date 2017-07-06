package br.com.missao.cleanarchitecture.injections.components

import br.com.missao.cleanarchitecture.injections.modules.*
import br.com.missao.cleanarchitecture.mvp.MainMvpModelOperations
import dagger.Component
import javax.inject.Singleton

/**
 * Application's Domain Component
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RetrofitModule::class, ApiModule::class, LoggerModule::class,
        MapperModule::class, DomainModule::class))
interface DomainComponent {

    /**
     * Obtains [MainMvpModelOperations]
     */
    fun getMainMvpDomainOperations(): MainMvpModelOperations
}