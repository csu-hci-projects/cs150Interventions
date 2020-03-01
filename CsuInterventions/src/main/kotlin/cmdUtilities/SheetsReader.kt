//package edu.colostate.csedu.cmdUtilities
//
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
//import com.google.api.services.sheets.v4.Sheets
//
//import edu.colostate.csedu.DB
//import edu.colostate.csedu.db.entity.Assessment
//import edu.colostate.csedu.db.entity.MasteryOutcome
//import edu.colostate.csedu.db.entity.Student
//
//
///**
// *
// *
// *
// * @author Albert Lionelle <br>
// *         lionelle@colostate.edu <br>
// *         Computer Science Department <br>
// *         Colorado State University
// * @version 1.0
// */
//class SheetsReader {
//    private val service : Sheets
//    private val APPLICATION_NAME = "Sheets / Update Clients"
//
//    private val sheetID = "1mA_scEa69LQ9dX1gqcKWMpJdty6etZ1ZwjHXceKmwzw"
//
//    fun getOutcomes() : Map<String, Any> {
//        val map = mutableMapOf<String, Any>()
//        val range = "Outcomes - Exam 1!A1:BE"
//        val response = service.spreadsheets().values()
//                .get(sheetID, range)
//                .execute()
//        val values = response.getValues()
//        val topics = mutableMapOf<String, MutableMap<String, Int>>()
//        var counter = 0
//        for(header in values.first()) { // get headers first   - TODO: pull this into seperate methods, way to complex as is
//            val lower = (header as String).toLowerCase()
//            if(lower.contains("programming")) {  // ignoring everything else at this point
//               if(lower.endsWith("result")) {
//                   val topic = lower.substringBefore(" result")
//                   if(topics.contains(topic)) {
//                       topics.getValue(topic)["result"] = counter
//                   } else {
//                       topics[topic] = mutableMapOf("result" to counter)
//                   }
//               } else if(lower.endsWith("mastery points")) {
//                   val topic = lower.substringBefore(" mastery points")
//                   if(topics.containsKey(topic)) {
//                       topics.getValue(topic)["mastery"] = counter
//                   }else {
//                       topics[topic] = mutableMapOf("mastery" to counter)
//                   }
//               }
//            }
//            counter++
//        }
//        for(row in values.drop(1)) {
//            val canvasId = row[0].toString()
//            val topicMap = mutableMapOf<String, Map<String, Double>>()
//            for(topic in topics.keys) {
//                val resultValue = row[topics[topic]!!["result"]!!].toString().toDoubleOrNull()
//                val masteryValue = row[topics[topic]!!["mastery"]!!].toString().toDoubleOrNull()
//                if(resultValue == null || masteryValue == null) continue // skip empty ones
//                topicMap[topic] = mapOf("result" to resultValue, "mastery" to masteryValue)
//            }
//            map[canvasId] = topicMap
//        }
//
//        return map
//    }
//
//    fun getCanvasMap() : Map<String, String>{
//        val map = mutableMapOf<String, String>()
//        val range = "CanvasIds!A2:B"
//        val response = service.spreadsheets().values()
//                .get(sheetID, range)
//                .execute()
//        val values = response.getValues()
//        for(value in values) {
//            map[value[1].toString()] = value[0].toString()
//        }
//        return map
//    }
//
//    fun getStudents(canvasIds : Map<String, String>) : List<Student> {
//        val list = mutableListOf<Student>()
//        val range = "Everyone!B2:E"
//        val response = service.spreadsheets().values()
//                .get(sheetID, range)
//                .execute()
//        val values = response.getValues()
//        for(value in values) {
//            val csuid = value[0]
//            val fname = value[1]
//            val lname = value[2]
//            val email = value[3]
//            val canvasId = canvasIds[csuid]
//            if(canvasId.isNullOrEmpty()) continue
//            print("$csuid $fname $lname $email $canvasId")
//            list += Student(id=canvasId, canvasId = canvasId, csuid = csuid as String,
//                            fname = fname as String, lname = lname as String, email = email as String)
//        }
//        return list
//
//    }
//
//    init {
//        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
//        service = Sheets.Builder(httpTransport, GoogleCredentials.JSON_FACTORY, GoogleCredentials.getCredentials(httpTransport))
//                .setApplicationName(APPLICATION_NAME)
//                .build()
//    }
//
//
//}