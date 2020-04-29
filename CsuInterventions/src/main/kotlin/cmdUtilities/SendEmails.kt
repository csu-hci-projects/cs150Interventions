package edu.colostate.csedu.cmdUtilities

import edu.colostate.csedu.DB
import edu.colostate.csedu.db.entity.Campaign
import edu.colostate.csedu.db.entity.Resource
import kotlinx.serialization.json.json


fun main(){
    val personalizedTemplate = "unit3PersonalEmailBody";
    val personalizedCampaignID = "5inCETCdO6A4FOVpGzGr";
    val personalizedJsonData = getEmailData(personalizedTemplate,personalizedCampaignID,true);


//    val generalTemplate = "unit3GeneralEmailBody"
//    val generalCampaignID = "RsSe4fe227V3HH98cNjC";
//    val generalJsonData = getEmailData(generalTemplate,generalCampaignID,false);
//
//
//    println("General Email JSon");
//    println(generalJsonData);
//    println();
    println("Personalized Email Json");
    println(personalizedJsonData)


}

fun getEmailData(template: String, campaignID: String, isPersonal: Boolean): String? {
    val emailSender = EmailBuilder(template, campaignID, isPersonal);
    val jsonData = emailSender.buildEmails();
    return jsonData
}