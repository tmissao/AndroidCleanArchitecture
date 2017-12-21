package br.com.missao.cleanarchitecture.injections.modules

import br.com.missao.cleanarchitecture.database.daos.RedditNewsDao
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

/**
 * Dao's Module
 */

@Module class DaoModule {

  /**
   * Provides [RedditNewsDao]
   */
  @Provides @Singleton fun providesRedditNewsDao(realm: Realm) = RedditNewsDao(realm)
}