package br.com.missao.cleanarchitecture.bases

import android.util.Log
import br.com.missao.cleanarchitecture.interfaces.DAO
import br.com.missao.cleanarchitecture.interfaces.Entity
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import java.util.*

/**
 * Base DAO implementing common methods
 */
abstract class DaoBase<T>(val realm: Realm, val clazz: Class<T>): DAO<T> where T: RealmObject, T:Entity {

  /**
   * Executes Realm's operations inside a transaction
   */
  inline fun transaction(element: T, crossinline body: (T) -> Unit) {
      realm.executeTransaction {
        body(element)
      }
  }

  /**
   * Gets element by [id]
   */
  override fun getById(id: String): T? {
    return realm.where(clazz).equalTo(Entity.ID_KEY, id).findFirst()
  }

  /**
   * Gets all elements from a class
   */
  override fun getAll(): RealmResults<T> {
    return realm.where(clazz).findAll()
  }

  /**
   * Inserts or updates an [element]
   */
  override fun save(element: T) {
    element.id = element.id ?: UUID.randomUUID().toString()
    transaction(element) { realm.insertOrUpdate(element) }
  }

  /**
   * Deletes a specific [element]
   */
  override fun delete(element: T) {
    element.id?.let { delete(it) }
  }

  /**
   * Deletes an element by [id]
   */
  override fun delete(id: String) {
    realm.where(clazz).equalTo(Entity.ID_KEY, id).findFirst()?.apply {
      transaction(this) {
        this.deleteFromRealm()
      }
    }
  }

  /**
   * Refreshes Realm instance
   */
  override fun refresh() {
    realm.refresh()
  }
}