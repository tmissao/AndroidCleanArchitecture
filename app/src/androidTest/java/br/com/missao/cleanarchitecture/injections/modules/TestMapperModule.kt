package br.com.missao.cleanarchitecture.injections.modules

import br.com.missao.cleanarchitecture.mappers.RedditNewsMapper
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application's Mappers
 */
@Module
class TestMapperModule {

    @Provides @Singleton fun providesRedditNewsMapper(): RedditNewsMapper
            = mock<RedditNewsMapper>()
}
