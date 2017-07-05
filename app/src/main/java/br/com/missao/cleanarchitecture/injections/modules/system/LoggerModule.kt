package br.com.missao.cleanarchitecture.injections.modules.system

import br.com.missao.cleanarchitecture.loggers.Logger
import br.com.missao.cleanarchitecture.loggers.ProductionLogger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application's Logger
 */
@Module
class LoggerModule {

    /**
     * Provides [Logger]
     */
    @Provides @Singleton fun providesLogger(): Logger
            = ProductionLogger()
}
