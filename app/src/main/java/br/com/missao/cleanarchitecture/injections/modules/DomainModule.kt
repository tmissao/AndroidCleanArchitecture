package br.com.missao.cleanarchitecture.injections.modules

import br.com.missao.cleanarchitecture.apis.reddit.RedditAPI
import br.com.missao.cleanarchitecture.domains.RedditDomain
import br.com.missao.cleanarchitecture.loggers.Logger
import br.com.missao.cleanarchitecture.mappers.RedditNewsMapper
import br.com.missao.cleanarchitecture.mvp.MainMvpModelOperations
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application's Domain module
 */
@Module
class DomainModule {

    @Provides @Singleton fun providesMainDomain(api: RedditAPI, logger: Logger,
                                                mapper: RedditNewsMapper): MainMvpModelOperations
            = RedditDomain(api, logger, mapper)
}