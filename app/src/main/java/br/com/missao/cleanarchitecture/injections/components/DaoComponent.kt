package br.com.missao.cleanarchitecture.injections.components

import br.com.missao.cleanarchitecture.database.daos.RedditNewsDao
import br.com.missao.cleanarchitecture.injections.modules.AppModule
import br.com.missao.cleanarchitecture.injections.modules.DaoModule
import dagger.Component
import javax.inject.Singleton


/**
 * Dao's component
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, DaoModule::class))
interface DaoComponent {

  /**
   * Obtains [RedditNewsDao]
   */
  fun getRedditNewsDao(): RedditNewsDao
}