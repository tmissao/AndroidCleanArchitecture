package br.com.missao.cleanarchitecture.injections.modules

import br.com.missao.cleanarchitecture.loggers.Logger
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application's Logger
 */
@Module
class TestLoggerModule {

    @Provides @Singleton fun providesLogger(): Logger
            = mock<Logger>()
}
