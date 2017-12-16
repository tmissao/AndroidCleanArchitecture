package br.com.missao.cleanarchitecture.bases

import br.com.missao.cleanarchitecture.interfaces.DAO
import br.com.missao.cleanarchitecture.interfaces.Entity
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import java.util.*

/**
 * Base DAO implementing common methods
 */
abstract class DaoBase<T>(val clazz: Class<T>): DAO<T> where T: RealmObject, T:Entity {

  /**
   * Executes realm's operations returning [X]
   */
  inline fun <X> execute(body: (Realm) -> X) : X? {
    return with(Realm.getDefaultInstance()) {
      val result = body(this)
      this.close()
      result
    }
  }

  /**
   * Executes realm's operations returning a list of [X]
   */
  inline fun <X> executeList(body: (Realm) -> RealmResults<X>) : RealmResults<X> {
    return with(Realm.getDefaultInstance()) {
      val result = body(this)
      this.close()
      result
    }
  }

  /**
   * Executes Realm's operations inside a transaction
   */
  inline fun transaction(element: T, crossinline body: (T) -> Unit) {
    execute {
      it.executeTransaction {
        body(element)
      }
    }
  }

  /**
   * Gets element by [id]
   */
  override fun getById(id: String): T? {
    return execute {
      it.where(clazz).equalTo(Entity.ID_KEY, id).findFirst()
    }
  }

  /**
   * Gets all elements from a class
   */
  override fun getAll(): RealmResults<T> {
    return executeList { it.where(clazz).findAll() }
  }

  /**
   * Inserts or updates an [element]
   */
  override fun save(element: T) {
    element.id = element.id ?: UUID.randomUUID().toString()
    execute { transaction(element) { it.realm.insertOrUpdate(element)} }
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
    execute { it.where(clazz).equalTo(Entity.ID_KEY, id).findFirst()?.deleteFromRealm() }
  }

  /**
   * Refreshes Realm instance
   */
  override fun refresh() {
    execute { it.refresh() }
  }
}