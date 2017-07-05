package br.com.missao.cleanarchitecture.injections.components.system

import br.com.missao.cleanarchitecture.injections.modules.system.ApiModule
import br.com.missao.cleanarchitecture.injections.modules.system.DomainModule
import br.com.missao.cleanarchitecture.injections.modules.system.LoggerModule
import br.com.missao.cleanarchitecture.injections.modules.system.RetrofitModule
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