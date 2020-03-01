package edu.colostate.csedu.cmdUtilities

import edu.colostate.csedu.util.canvas.CanvasStudent
import edu.colostate.csedu.util.canvas.loadCanvasCsv
import edu.colostate.csedu.util.canvas.loadEmailCsv

import edu.colostate.csedu.DB
import edu.colostate.csedu.db.entity.Student

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



// this application should only  be ran once!
fun main(args: Array<String>) {  // args[0] - canvas file, args[1] emails with student IDs
    println("Loading ${args[0]} ....")


    val canvasStudents = loadCanvasCsv(args[0]) // students loaded with canvas ids
    println(canvasStudents)
    val emails = loadEmailCsv(args[1])
    println(emails)


    val course = DB.courses.get(args[2]) ?: throw Exception("Invalid course id") // ok, really hacky way.

    val allStudents = DB.students.getAll()
    for(student in canvasStudents) {
        if(student is CanvasStudent) { // skip points possible for this.
            val found = allStudents.values.firstOrNull { it.canvasId == student.canvasId }
            var id : String
            id = if(found == null) {   // if the student doesn't exit, add it
                val newStudent = Student(canvasId = student.canvasId,
                        fname = student.fname,
                        lname = student.lname,
                        csuid = student.studentId,
                        email = emails[student.studentId] ?: "")
                newStudent.courses.add(course.id)
                DB.students.add(newStudent)  // this sets to id

            }else { // they exist, just grab the id
                found.courses.add(course.id)
                DB.students.set(found.id, found) // re-save with course added
                found.id
            }
            course.addStudent(id, student.section)
        }
    }
    DB.courses.set(course)

}
 