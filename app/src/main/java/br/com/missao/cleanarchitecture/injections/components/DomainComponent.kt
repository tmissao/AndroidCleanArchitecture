package br.com.missao.cleanarchitecture.injections.components

import br.com.missao.cleanarchitecture.injections.modules.ApiModule
import br.com.missao.cleanarchitecture.injections.modules.DomainModule
import br.com.missao.cleanarchitecture.injections.modules.LoggerModule
import br.com.missao.cleanarchitecture.injections.modules.RetrofitModule
import br.com.missao.cleanarchitecture.mvp.MainMvpModelOperations
import dagger.Component
import javax.inject.Singleton

/**
 * Application's Domain Component
 */
@Singleton
@Component(modules = arrayOf(RetrofitModule::class, ApiModule::class, LoggerModule::class, DomainModule::class))
interface DomainComponent {

    fun getMainMvpDomainOperations(): MainMvpModelOperations
}