package br.com.missao.cleanarchitecture.interfaces

import io.realm.RealmObject
import io.realm.RealmResults

/**
 * Common repository methods
 */
interface DAO<T> where T:RealmObject, T:Entity {

  /**
   * Gets element with [id]
   */
  fun getById(id: String) : T?

  /**
   * Gets all elements
   */
  fun getAll() : RealmResults<T>

  /**
   * Inserts or updates [element]
   */
  fun save(element: T)

  /**
   * Removes [element]
   */
  fun delete(element: T)

  /**
   * Removes element with [id]
   */
  fun delete(id: String)

  /**
   * Refreshes Realm
   */
  fun refresh()

}