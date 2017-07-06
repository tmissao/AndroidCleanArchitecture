package br.com.missao.cleanarchitecture.injections.components

import br.com.missao.cleanarchitecture.apis.reddit.RedditAPI
import br.com.missao.cleanarchitecture.injections.modules.ApiModule
import br.com.missao.cleanarchitecture.injections.modules.AppModule
import br.com.missao.cleanarchitecture.injections.modules.RetrofitModule
import dagger.Component
import javax.inject.Singleton

/**
 * Applications API components
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class, RetrofitModule::class))
interface ApiComponent {

    /**
     * Obtains [RedditAPI]
     */
    fun getRedditApi(): RedditAPI
}