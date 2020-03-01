package edu.colostate.csedu.db

import edu.colostate.csedu.db.entity.*


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
interface Database {
    var students  : Students
    var resources : Resources
    var outcomes  : Outcomes
    var courses : Courses
    var campaigns : Campaigns
    var clicks : Clicks

}

interface Clicks {
    fun set(click:Click)
    fun get(clickId:String) : Click?
}

interface Campaigns {
    fun get(campaignId:String) : Campaign?
    fun add(campaign: Campaign, course: Course) :  String

}

interface Courses {
    fun get(courseId:String) : Course?
    fun set(course:Course)

}

interface Students {
    fun get(studentId:String) : Student?
    fun getAll() : Map<String, Student>
    fun set(id:String, student:Student)
    fun add(student: Student) : String

}



interface Resources {
    fun getAll(): Map<String, Resource>
    fun set(id: String, resource: Resource)
}

interface Outcomes {
    fun getAll() : Map<String, Outcome>
    fun set(outcome: Outcome)
    fun add(outcome: Outcome)

}