package edu.colostate.csedu.cmdUtilities
import com.google.gson.Gson
import edu.colostate.csedu.db.entity.Student
import edu.colostate.csedu.view.EmailTemplates;
import edu.colostate.csedu.DB;
import edu.colostate.csedu.SETTINGS
import edu.colostate.csedu.db.entity.Click


class EmailBuilder(private val templateString: String, private val campaignID: String, private val isPersonal: Boolean){

    private val allResources = DB.resources.getAll();
    private val outcomes = DB.outcomes.getAll();

    fun buildEmails(): String? {
        val studentsInCampaign = getStudentsInCampaign();

        val jsonData = mutableListOf<Map<String, Any>>();
        val emailTemplator = EmailTemplates();
        val template = emailTemplator.compileTemplate(templateString);

        for(student in studentsInCampaign){
            val resourceMap = getStudentResourceMap(student);
            if(!(resourceMap.isEmpty() || student.email.isEmpty())){
                val mut = mutableMapOf<String,Any>("resources" to resourceMap);
                mut+=student.toMap()
                mut["courseId"] = "CS150 Spring 2020";
                val map = mutableMapOf<String, Any>();
                map["to"] = student.email;
                map["subject"] = "CS 150: Unit Three - Resources";
                map["body"] = emailTemplator.applyTemplate(template, mut).replace("\n", "");
                jsonData+=map;
            }
        }
        return Gson().toJson(jsonData);
    }

    private fun getStudentResourceMap(student: Student): MutableMap<String, Any>{
        val resourceMap = mutableMapOf<String, Any>();
        for(topic in outcomes.values){
            val topicID = topic.topic;
            if(isPersonal){
                if(student.outcomeMapping[topicID]?.score!! < student.outcomeMapping[topicID]?.mastery!! && student.outcomeMapping[topicID]?.score!! != 0.0){
                    resourceMap[topicID] = getListOfResources(topicID, student);
                }
            } else {
                resourceMap[topicID] = getListOfResources(topicID, student);
            }

        }
        return resourceMap;
    }


    private fun getListOfResources(topicID: String, student: Student): MutableList<Map<String, String>> {
        val resourceList = mutableListOf<Map<String,String>>();
        for(resource in allResources.values.filter { it.topic == topicID }){
                val click = Click(
                        resourceId=resource.id,
                        studentId = student.id,
                        url = resource.url,
                        campaignId = campaignID)

                click.id = Click.findUnique(5, DB)
                DB.clicks.set(click);
                val url = SETTINGS.HOST + "/" + click.id;
                val link = mapOf("name" to resource.name,
                              "url" to url);
                resourceList.add(link);


        }
        return resourceList;
    }


    private fun getStudentsInCampaign():MutableList<Student>{
        val studentIDs = DB.campaigns.get(campaignID)?.studentIds;
        val students = mutableListOf<Student>();
        if (studentIDs != null) {
            for(studentID in studentIDs){
                val student = allStudents[studentID];
                if (student != null) {
                    students.add(student)
                };
            }
        }
        return students;
    }

}