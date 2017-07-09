package br.com.missao.cleanarchitecture.injections.modules

import android.content.Context
import br.com.missao.cleanarchitecture.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application's module
 */
@Module class TestAppModule(val app: App) {

    @Provides @Singleton fun providesApp() = app

    @Provides @Singleton fun providesContext(): Context = app
}