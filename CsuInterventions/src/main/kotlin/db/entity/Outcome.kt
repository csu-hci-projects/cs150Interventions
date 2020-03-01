package edu.colostate.csedu.db.entity

import kotlinx.serialization.*

@Serializable
data class Outcome(var description: String="",
                   var topic :  String = "",
                   var friendlyName: String = "",
                   var mastery: Double = 4.0,
                   var calculationMethod: String = OutcomeCalculationMethods.LATEST,
                   var calculationInt: Int = 0): Mappable()
@Serializable
data class OutcomeResult(var outcomeId: String = "", var score:  Double = 0.0,  var mastery: Double = 0.0,
                         var links : MutableMap<String, String> = mutableMapOf())  // links include paths in DB



object OutcomeCalculationMethods {
   const val LATEST = "LATEST"
   const val  DECAYING_AVERAGE = "DECAYING_AVERAGE"
   const val HIGHEST = "HIGHEST"
   const val N_MASTERY = "N_MASTERY"
}

