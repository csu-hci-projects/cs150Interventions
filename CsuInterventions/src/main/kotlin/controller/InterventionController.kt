package edu.colostate.csedu.controller


import com.google.gson.Gson
import edu.colostate.csedu.DB
import edu.colostate.csedu.db.entity.*
import edu.colostate.csedu.view.EmailTemplates
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger(InterventionController::class.java)
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
object InterventionController {
    /* //TODO  - remove this file, leaving code to see how  it was done but may  be
    //  easier to start from scratch  anyway
    fun massEmail(courseId : String, assessmentId: String, templateId: String): String { //http://localhost:8080/cs150Fall19/test-unit/email/unit1EmailBody
        val ls = DB.getAllStudents(courseId)
        val templator = EmailTemplates()

        val template = templator.compileTemplate(templateId)
        val json = mutableListOf<Map<String, Any>>()

        for(student in ls) {

            val assessment : Assessment
            try {
                assessment = DB.getAssessment(student, assessmentId)
            } catch(ex :Exception) {  // really hacky way to do this - fix in the future
                continue
            }
            val mut = DB.getAllResources().toResourceMap(student, assessment)
            if(mut.isEmpty() || student.email.isEmpty()) continue // don't add to json if the email doesn't exist
            mut += student.toMap()

            val map = mutableMapOf<String, Any>()
            map["to"] = student.email
            map["subject"] = "CS 150: Unit One - Resources"//todo, pull this from the title of the webpage

            map["body"] = templator.applyTemplate(template, mut).replace("\n", "")
            json += map
        } //TODO: Get it to email directly, and not via the massEmailer app!
        return Gson().toJson(json)
    }


   private fun List<Resource>.toResourceMap(student: Student, assessment : Assessment, maxDepth : Int = 1) : MutableMap<String, Any> {
       val map = mutableMapOf<String, Any>()
       // for this particular case, I am sorting the resources by topics, and using the info for the template
       val innerMap = mutableMapOf<String, MutableList<Map<String, Any>>>()
       val anyTopics = mutableMapOf<String, Map<String, Any>>()
       for(resource in this) {
           if(resource.depth < maxDepth) {
               if (assessment.matchesResourceTopic(resource.topic)) {
                   if (innerMap.containsKey(resource.topic)) {
                       innerMap[resource.topic]!!.add(resource.toMap(student))
                   } else {
                       innerMap[resource.topic] = mutableListOf(resource.toMap(student))
                   }
               }else if (resource.topic == "any") { // most of these are direct access, so just appending them
                   anyTopics[resource.id] = resource.toMap(student)
               }
           }

       }
       if(innerMap.isEmpty()) return map // early return
       map["resources"] = innerMap + anyTopics
       return map
    }

    private fun Assessment.matchesResourceTopic(topic : String) : Boolean{
        when(this) {
            is MasteryOutcome -> {
                if (this.topics.containsKey(topic)) {
                    val mp = this.topics[topic] as Map<String, Double>
                    if(mp.getValue("result") < mp.getValue("mastery")) { //TODO find better way to do this without hard coding!
                        return true
                    }
                }
                return false
            }
            is UnknownAssessment -> return false
            else -> return false
        }
    }*/
}