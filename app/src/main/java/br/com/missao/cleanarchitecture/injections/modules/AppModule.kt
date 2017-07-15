package br.com.missao.cleanarchitecture.injections.modules

import android.content.Context
import br.com.missao.cleanarchitecture.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application's module
 */
@Module class AppModule(val app: App) {

    /**
     * Provides [App]
     */
    @Provides @Singleton fun providesApp() = app

    /**
     * Provides [Context]
     */
    @Provides @Singleton fun providesContext(): Context = app.baseContext
}