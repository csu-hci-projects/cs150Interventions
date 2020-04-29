package edu.colostate.csedu.cmdUtilities
import edu.colostate.csedu.DB;
import edu.colostate.csedu.db.entity.Student

fun main(){
    val personalCampaignID = "5inCETCdO6A4FOVpGzGr";
    val generalCampaignID = "RsSe4fe227V3HH98cNjC";
    val clicks = DB.clicks.getAll();
    println("Printing Clicks from personal campaign");
    for(click in clicks.values.filter { it.campaignId == personalCampaignID}){
        println(click);
    }
    println("Printing Clicks from general campaign");
    for(click in clicks.values.filter { it.campaignId == generalCampaignID}){
        println(click);
    }
}