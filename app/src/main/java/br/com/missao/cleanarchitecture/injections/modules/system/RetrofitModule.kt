package br.com.missao.cleanarchitecture.injections.modules.system

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Retrofit's Module
 */
@Module
class RetrofitModule {

    /**
     * Provides API base URL
     */
    @Provides @Singleton fun providesBaseUrl()
            = "https://www.reddit.com"

    /**
     * Provides [OkHttpClient] with an [HttpLoggingInterceptor] for debug purposes
     */
    @Provides @Singleton fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    /**
     * Provides [Retrofit] rest client on the [baseUrl], and uses [client] to log its requests
     */
    @Provides @Singleton fun providesRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}