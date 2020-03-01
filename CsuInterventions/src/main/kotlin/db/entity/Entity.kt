package edu.colostate.csedu.db.entity

import com.google.appengine.repackaged.com.google.gson.Gson
import com.google.appengine.repackaged.com.google.gson.reflect.TypeToken


/**
 *
 * Basic classes that all entities in the database can use.
 *
 * @author Albert Lionelle <br>
 *         lionelle@colostate.edu <br>
 *         Computer Science Department <br>
 *         Colorado State University
 * @version 202010
 */

/**
 * Converts a data object from a object to map. May be a better way to do this
 */
open class Mappable {
    @Transient
    private val gson = Gson()

    @Transient
    var id: String = ""

    //convert a data class to a map
    fun toMap(): Map<String, Any> {
        val json = gson.toJson(this)
        return gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
    }

    fun toJson() : String = gson.toJson(this)

}
