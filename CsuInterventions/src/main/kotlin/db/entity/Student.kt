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
data class Student(var canvasId:String = "", var csuid:String = "",
                   var email:String="", var fname: String="",
                   var lname:String="",
                   var courses: MutableList<String> = mutableListOf()) : Mappable() {

    val outcomeMapping = mutableMapOf<String, StudentOutcome>()

    fun addToOutcomeResult(result: OutcomeResult) {
        outcomeMapping[result.outcomeId]?.history?.add(result)
    }


}

@Serializable
data class StudentOutcome(var score: Double = 0.0, var mastery: Double = 4.0,
                          var calculationMethod: String = OutcomeCalculationMethods.LATEST,
                          var calculationInt: Int = 0, val history:MutableList<OutcomeResult> = mutableListOf()) : Mappable()

/*
    @Transient
    var courseId : String = ""

    private val clicks = mutableListOf<Click>()

    val assessments: MutableList<Assessment> = mutableListOf()

    /**
     * Adds a clickThrough counter to the student in the database. Automatically saves
     */
    fun addClick(resource: Resource) {
        val click = Click(resource)
        DB.addClickToStudent(this, click)
        clicks += click
    }

    /**
     * Doing tomap as I couldn't get the json stringify working with assessments
     */
    fun toMap(includeAssessment : Boolean = false): Map<String, Any> {
        val map = mutableMapOf<String, Any>(
                "fname" to fname,
                "lname" to lname,
                "canvasId" to canvasId,
                "id" to id,
                "email" to email,
                "csuid" to csuid)
        if(courseId.isNotEmpty()) { map["courseId"] = courseId }
        if(includeAssessment) {
            val asMap = mutableListOf<Map<String, Any>>()
            for (assessment in assessments) {
                asMap += assessment.toMap()
            }
            map["assessments"] = asMap
        }
        return map

    }
}

@Serializable
data class Click(var resource: Resource) {
    fun toMap() : Map<String, Any> {
        return resource.toMap()
    }
*/

