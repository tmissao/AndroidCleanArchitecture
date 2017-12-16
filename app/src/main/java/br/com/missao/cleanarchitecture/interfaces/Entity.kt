package br.com.missao.cleanarchitecture.interfaces

/**
 * Entity Interfaceß
 */
interface Entity {

  companion object {
    val ID_KEY: String
      get() = "id"
  }

  /**
   * Entity unique key
   */
  var id: String?
}