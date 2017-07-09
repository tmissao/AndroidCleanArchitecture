package br.com.missao.cleanarchitecture.injections.modules

import br.com.missao.cleanarchitecture.apis.reddit.RedditAPI
import br.com.missao.cleanarchitecture.loggers.Logger
import br.com.missao.cleanarchitecture.mappers.RedditNewsMapper
import br.com.missao.cleanarchitecture.mvp.MainMvpModelOperations
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application's Domain module
 */
@Module
class TestDomainModule {

    @Provides @Singleton fun providesMainDomain(api: RedditAPI, logger: Logger,
                                                mapper: RedditNewsMapper): MainMvpModelOperations
            = mock<MainMvpModelOperations>()
}