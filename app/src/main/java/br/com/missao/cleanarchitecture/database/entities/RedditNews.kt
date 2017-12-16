package br.com.missao.cleanarchitecture.entities

import android.support.v7.widget.DialogTitle
import br.com.missao.cleanarchitecture.interfaces.Entity
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * RedditNews Entity persisted on database
 */
class RedditNews() : RealmObject(), Entity {

  @PrimaryKey override var id: String? = null
  var author: String
  var title: String
  var numComments: Int
  var thumbnail: String
  var url: String

  init {
    this.id = null
    this.author = ""
    this.title = ""
    this.numComments = 0
    this.thumbnail = ""
    this.url = ""
  }
  
  constructor(author: String, title: String, numComments: Int, thumbnail: String,
              url: String, id: String? = UUID.randomUUID().toString()) : this() {
    this.id = id
    this.author = author
    this.title = title
    this.numComments = numComments
    this.thumbnail = thumbnail
    this.url = url
  }
}