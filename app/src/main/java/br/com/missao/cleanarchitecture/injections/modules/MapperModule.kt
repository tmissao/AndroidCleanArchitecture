package br.com.missao.cleanarchitecture.injections.modules

import br.com.missao.cleanarchitecture.mappers.RedditNewsMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application's Mappers
 */
@Module
class MapperModule {

    /**
     * Provides [RedditNewsMapper]
     */
    @Provides @Singleton fun providesRedditNewsMapper(): RedditNewsMapper
            = RedditNewsMapper()
}
