package br.com.missao.cleanarchitecture.injections.modules

import br.com.missao.cleanarchitecture.mvp.MainMvpModelOperations
import br.com.missao.cleanarchitecture.mvp.MainMvpPresenterOperations
import br.com.missao.cleanarchitecture.presenters.RedditPresenter
import dagger.Module
import dagger.Provides

/**
 * Application's Presenter Module
 */
@Module
class PresenterModule {

    @Provides fun providesMainPresenter(domain: MainMvpModelOperations): MainMvpPresenterOperations
            = RedditPresenter(domain)
}