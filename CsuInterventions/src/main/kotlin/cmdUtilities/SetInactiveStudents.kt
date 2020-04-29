package edu.colostate.csedu.cmdUtilities

import GetActiveStudents
import edu.colostate.csedu.DB
import edu.colostate.csedu.db.entity.StudentInCourse

const val courseID = "cs150Spring2020";
val activeStudentsIDs = GetActiveStudents("src/main/currentStudents.csv").getActiveStudentIDs();
val allStudents = DB.students.getAll();
val course = DB.courses.get("cs150Spring2020");
val studentsInCourse = course?.studentsInCourse;

fun main(){
    val mapOfStudentIDs = getMapOfStudentIDtoCanvasID(studentsInCourse);
    val inactiveStudentsInCourse = getInactiveStudentInCourse(mapOfStudentIDs);
    //setInactiveStudents(inactiveStudentsInCourse);
}

fun getMapOfStudentIDtoCanvasID(studentsInCourse: MutableMap<String, StudentInCourse>?): MutableMap<String, String>{
    val map = mutableMapOf<String, String>();
    if (studentsInCourse != null) {
        for(student in studentsInCourse.values){
            val csuID = allStudents[student.studentId]?.csuid;
            if (csuID != null) {
                map[csuID] = student.studentId;
            }
        }
    }
    return map;
}

fun getInactiveStudentInCourse(idMap: MutableMap<String, String>): MutableList<String>{
    val inactiveStudentsInCourse = mutableListOf<String>()
    for(id in idMap){
        if(id.key !in activeStudentsIDs){
            inactiveStudentsInCourse.add(id.value);
        }
    }
    return inactiveStudentsInCourse;
}

fun setInactiveStudents(inactiveStudents: MutableList<String>){
    for(studentID in inactiveStudents){
        course?.setStudentInactive(studentID);
    }
    if (course != null) {
        DB.courses.set(course)
    };
}