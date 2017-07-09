package br.com.missao.cleanarchitecture.injections.modules

import br.com.missao.cleanarchitecture.apis.reddit.RedditAPI
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Application's API module
 */
@Module
class TestApiModule {

    @Provides @Singleton fun providesRedditApi(retrofit: Retrofit): RedditAPI
            = mock<RedditAPI>()
}