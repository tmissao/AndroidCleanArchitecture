package br.com.missao.cleanarchitecture.database.daos

import br.com.missao.cleanarchitecture.bases.DaoBase
import br.com.missao.cleanarchitecture.database.entities.RedditNews
import io.realm.Realm

/**
 * RedditNews' Dao
 */
class RedditNewsDao(realm: Realm) : DaoBase<RedditNews>(realm, RedditNews::class.java)