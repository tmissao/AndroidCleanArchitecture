package br.com.missao.cleanarchitecture.injections.components

import br.com.missao.cleanarchitecture.injections.modules.LoggerModule
import br.com.missao.cleanarchitecture.loggers.Logger
import dagger.Component
import javax.inject.Singleton

/**
 * Application's Logger component
 */
@Singleton
@Component(modules = arrayOf(LoggerModule::class))
interface LoggerComponent {

    /**
     * Obtains [Logger]
     */
    fun getLogger(): Logger
}