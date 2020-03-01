package edu.colostate.csedu.db.entity

import kotlinx.serialization.Serializable




@Serializable
data class Resource(var name : String ="", var topic : String="",
                    var url: String="", var depth:Int =0,
                    var mediaType : String = "static") : Mappable()

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
//@Serializable
//data class Resource(var id: String ="", var name : String ="", var topic : String="",
//                    var url: String="", var depth:Int =0,
//                    var mediaType : String = "static") {
//
//
//
//    fun buildStudentUrl(student: Student) = "${SETTINGS.HOST}/${student.courseId}/client/${student.id}/resource/$id"
//
//
//    fun toMap(student : Student?=null) : Map<String, Any> {
//        val map = mutableMapOf(
//                "id" to id,
//                "name" to name,
//                "topic" to topic,
//                "url" to url,
//                "depth" to depth,
//                "mediaType" to mediaType
//        )
//        if (student != null) {
//            map["student-url"] = buildStudentUrl(student)
//        }
//
//        return map
//    }
//
//
//}