package edu.colostate.csedu.cmdUtilities

import edu.colostate.csedu.DB
import edu.colostate.csedu.db.entity.*

/**
 * Simple test method as I rework the student tables
 */
fun main(args: Array<String>) {
    //1 - save inactive to database
    //2 - save campaign to database
    //3 - save outcome to database

    //4
    //make sure we can access our handle bars and print something out
    //make sure we can print something based on if they have met expectations
    //start building the email string
    //handle bars are templated maps(json object)
    //map of links, links are click object
    //passing in the object for every student to build a string(email body)
}

fun addClickDefaultTest() {
    val students = DB.students.getAll()

    val resources = DB.resources.getAll()

    val campaign = DB.campaigns.get("X4LROszpqYbGgeoNp82y") // test  campaign ID

    for(resource in resources.values.filter { it.depth < 1 }) {
        for(student in students.values) {
            val click = Click(resourceId = resource.id,
                    studentId = student.id,
                    url = resource.url,
                    campaignId = campaign!!.id)
            click.id = Click.findUnique(5, DB)
   //         DB.clicks.set(click)
        }
    }

}