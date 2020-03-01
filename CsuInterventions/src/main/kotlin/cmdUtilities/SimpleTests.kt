package edu.colostate.csedu.cmdUtilities

import edu.colostate.csedu.DB
import edu.colostate.csedu.db.entity.Campaign
import edu.colostate.csedu.db.entity.Click
import edu.colostate.csedu.db.entity.Outcome

/**
 * Simple test method as I rework the student tables
 */
fun main(args: Array<String>) {

    val students = DB.students.getAll()




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
            DB.clicks.set(click)
        }
    }

}