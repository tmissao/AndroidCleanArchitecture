package br.com.missao.cleanarchitecture.injections.modules

import br.com.missao.cleanarchitecture.apis.reddit.RedditAPI
import br.com.missao.cleanarchitecture.apis.reddit.RedditRest
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Application's API module
 */
@Module
class ApiModule {

    /**
     * Provides [RedditAPI] using [retrofit] as implementation
     */
    @Provides @Singleton fun providesRedditApi(retrofit: Retrofit): RedditAPI
            = RedditRest(retrofit)
}