package br.com.missao.cleanarchitecture.injections.components

import br.com.missao.cleanarchitecture.injections.modules.MapperModule
import br.com.missao.cleanarchitecture.mappers.RedditNewsMapper
import dagger.Component
import javax.inject.Singleton

/**
 * Applications's Mapper Component
 */
@Singleton
@Component(modules = arrayOf(MapperModule::class))
interface MapperComponent {

    /**
     * Obtains [RedditNewsMapper]
     */
    fun getRedditNewsMapper(): RedditNewsMapper
}