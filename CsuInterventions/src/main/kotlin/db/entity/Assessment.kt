package edu.colostate.csedu.db.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule

val messageModule = SerializersModule { // 1
    polymorphic(Assessment::class) { // 2
        MasteryOutcome::class with MasteryOutcome.serializer() // 3
        UnknownAssessment::class with UnknownAssessment.serializer() // 4
    }
}

/**
 *
 *
 *
 * @author Albert Lionelle <br>
 *         lionelle@colostate.edu <br>
 *         Computer Science Department <br>
 *         Colorado State University
 * @version 1.0
 */
@Serializable
sealed class Assessment {
    abstract val name:String
    abstract val id: String

    abstract val type: String

    abstract fun toMap() : Map<String, Any>
    abstract fun getData() : Map<String, Any>

    companion object {
        const val MASTERY = "mastery"


        fun builder(id: String, data: Map<String, Any>?) : Assessment {
            if(data.isNullOrEmpty() ||
               data.get("name") == null ||
               data.get("type") == null) return UnknownAssessment("NO DATA", type="NO DATA", id = id)

            return when(data["type"]) {
                MASTERY -> {
                    MasteryOutcome(name = data["name"] as String, id = id,
                            topics = data["topics"] as Map<String, Map<String, Double>>)
                }
                else -> UnknownAssessment(data["name"] as String, type = data["type"] as String, id = id)
            }
        }
    }
}


@Serializable
data class MasteryOutcome(override var name: String, override var id: String,
                          var topics : Map<String, Map<String, Double>>) : Assessment() {
    override val type: String
        get(){return MASTERY}

    override fun toMap() : Map<String, Any> = mapOf(
            "name" to name,
            "id" to id,
            "type" to type,
            "topics" to topics
    )
    override fun getData() : Map<String, Any> =  topics

}

@Serializable
data class UnknownAssessment(override var name: String, override var id: String, override var type : String) : Assessment() {

    override fun toMap() : Map<String, Any> = mapOf(
            "name" to name,
            "id" to id,
            "type" to type
    )

    override fun getData(): Map<String, Any> = mapOf()
}
