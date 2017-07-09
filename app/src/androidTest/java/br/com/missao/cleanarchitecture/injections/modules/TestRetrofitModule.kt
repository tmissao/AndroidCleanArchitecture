package br.com.missao.cleanarchitecture.injections.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Retrofit's Module
 */
@Module
class TestRetrofitModule {

    @Provides @Singleton fun providesBaseUrl()
            = "http://github.com"

    @Provides @Singleton fun providesOkHttpClient(): OkHttpClient
            = OkHttpClient.Builder().build()

    @Provides @Singleton fun providesRetrofit(client: OkHttpClient, baseUrl: String): Retrofit
            = Retrofit.Builder().baseUrl(baseUrl).build()

}