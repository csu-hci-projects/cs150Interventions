package edu.colostate.csedu.cmdUtilities

import edu.colostate.csedu.DB
import edu.colostate.csedu.db.entity.Campaign
import edu.colostate.csedu.db.entity.StudentStates

fun main () {
    val activeStudents = getActiveStudentDatabaseIDs();
    val(generalCampaign, personalizedCampaign) = getCampaigns(activeStudents);
    if (course != null) {
        println(generalCampaign.studentIds.size);
        println(personalizedCampaign.studentIds.size);
        DB.campaigns.add(generalCampaign, course);
        DB.campaigns.add(personalizedCampaign, course);
    }
}

    fun getActiveStudentDatabaseIDs(): MutableList<String> {
        val activeStudentDatabaseIDs = mutableListOf<String>();
        if (studentsInCourse != null) {
            for (student in studentsInCourse.values) {
                if (student.status == StudentStates.ACTIVE) {
                    activeStudentDatabaseIDs.add(student.studentId);
                }
            }
        }
        return activeStudentDatabaseIDs;
    }

fun getCampaigns(activeStudents: MutableList<String>): Pair<Campaign, Campaign> {
    activeStudents.shuffle();
    val generalCampaignIDs = mutableListOf<String>();
    val personalizedCampaignIDs = mutableListOf<String>();
    val middleIndex = activeStudents.size/2;
    for(i in 0 until activeStudents.size) {
        if (i < middleIndex) {
            generalCampaignIDs.add(activeStudents[i]);
        } else {
            personalizedCampaignIDs.add(activeStudents[i]);
        }
    }
    val generalCampaign = Campaign("General-Unit3 (4)",courseID, Campaign.Companion.TYPE.OUTCOMES, generalCampaignIDs);
    val personalCampaign = Campaign("Personalized-Unit3 (4)",courseID, Campaign.Companion.TYPE.OUTCOMES, personalizedCampaignIDs);
    return Pair(generalCampaign,personalCampaign);
}
