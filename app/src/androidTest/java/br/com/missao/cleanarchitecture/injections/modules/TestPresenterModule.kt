package br.com.missao.cleanarchitecture.injections.modules

import br.com.missao.cleanarchitecture.mvp.MainMvpPresenterOperations
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application's Presenter Module
 */
@Module
class TestPresenterModule {

    @Provides @Singleton fun providesMainPresenter(): MainMvpPresenterOperations
            = mock()
}