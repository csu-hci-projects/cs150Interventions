package edu.colostate.csedu.db.firestore

import edu.colostate.csedu.db.Campaigns
import edu.colostate.csedu.db.entity.Campaign
import edu.colostate.csedu.db.entity.Course


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
class CampaignsFb(val db: Firebase) : Campaigns {

    override fun get(campaignId: String): Campaign? {
        val document = db.getDocument(CAMPAIGN_TABLE_NAME, campaignId)
        document?.let {
            val campaign = document.toObject(Campaign::class.java)
            campaign?.id = document.id
            return campaign
        }
        return null
    }


    override fun add(campaign: Campaign, course: Course):String {
        val document = db.addDocument(CAMPAIGN_TABLE_NAME, campaign.toMap())
        val id = document.get().get().id
        course.campaignIds.add(id)
        db.setDocument(CoursesFb.COURSES_TABLE_NAME, course.id, course.toMap())
        return id
    }

    companion object {
        private const val CAMPAIGN_TABLE_NAME = "campaigns"
    }

}