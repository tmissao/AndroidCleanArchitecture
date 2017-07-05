package br.com.missao.cleanarchitecture.injections.components.system

import br.com.missao.cleanarchitecture.apis.reddit.RedditAPI
import br.com.missao.cleanarchitecture.injections.modules.system.ApiModule
import br.com.missao.cleanarchitecture.injections.modules.system.RetrofitModule
import dagger.Component
import javax.inject.Singleton

/**
 * Applications API components
 */
@Singleton
@Component(modules = arrayOf(ApiModule::class, RetrofitModule::class))
interface ApiComponent {

    /**
     * Obtains [RedditAPI]
     */
    fun getRedditApi(): RedditAPI
}