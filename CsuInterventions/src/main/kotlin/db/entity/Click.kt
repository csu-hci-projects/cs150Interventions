package edu.colostate.csedu.db.entity

import edu.colostate.csedu.db.Database
import kotlinx.serialization.Serializable
import kotlin.random.Random

val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890"

/**
 *
 * Basic click tracker
 *
 * @author Albert Lionelle <br>
 *         lionelle@colostate.edu <br>
 *         Computer Science Department <br>
 *         Colorado State University
 * @version 202010
 */
@Serializable
data class Click(val resourceId:String ="",
                 val studentId:String = "",
                 val campaignId:String = "",
                 val url:String = "", // technically could be pulled from the resource, but here for easy access
                 val accessed:MutableList<String> = mutableListOf()) : Mappable() {


    companion object {
        /**
         * Finds a unique identifier for the click, so we can keep the length short
         * TODO: update this so it auto-detects too many collisions, and updates the length
         */
        fun findUnique(length: Int = 5, db: Database): String {
            var str:String
            val random  = Random.Default
            do {
                str = ""
                for(i in 0 until length) str += characters[random.nextInt(characters.length)]
            }while(db.clicks.get(str) != null) // keeps looping until it doesn't have a conflict
            return str
        }
    }

}