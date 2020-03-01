package edu.colostate.csedu.db.entity

import kotlinx.serialization.Serializable


/**
 *
 *
 *
 * @author Albert Lionelle <br>
 *         lionelle@colostate.edu <br>
 *         Computer Science Department <br>
 *         Colorado State University
 * @version 202010
 */
@Serializable
data class Campaign(var name:String = "",
                    var courseId:String = "",
                    var type: String = TYPE.OUTCOMES,
                    val studentIds:MutableList<String> = mutableListOf()) :  Mappable() {

    companion object {
        object TYPE {
            const val OUTCOMES = "OUTCOMES"
        }
    }

}

