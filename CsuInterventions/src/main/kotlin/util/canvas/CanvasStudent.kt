package edu.colostate.csedu.util.canvas

import edu.colostate.csedu.util.file.parseCSVLine


/**
 *
 *
 *
 * @author Albert Lionelle <br>
 *         lionelle@colostate.edu <br>
 *         Computer Science Department <br>
 *         Colorado State University
 * @version 1.0
 */
open class CanvasData(val data : MutableMap<String,String> = mutableMapOf()) // this would actually be a good case for a sealed class

class CanvasPointsPossible() : CanvasData()

data class CanvasStudent(val canvasId:String,  val studentId:String,
                         val fname:String, val lname:String,
                         val section:String) : CanvasData()


fun convertLineToStudentData(line:String, headers:List<String>) :  CanvasData? {
    val values = line.parseCSVLine()
    val first = values[0].trim()
    if(first.isEmpty()) return null

    var student : CanvasData
    if(first != "Points Possible") {
        student = CanvasStudent(fname = first.split(",")[1].trim(),
                lname = first.split(",")[0].trim(),
                canvasId = values[1],
                studentId = values[2], // ename doesn't do much anymore with change from rams to standard email
                section = values[4])
    }else {
        student = CanvasPointsPossible()
    }
    val rawData = values.subList(5, values.size)
    var i = 0
    for(header in headers) {
        student.data[header] = rawData[i++]
    }
    return student
}