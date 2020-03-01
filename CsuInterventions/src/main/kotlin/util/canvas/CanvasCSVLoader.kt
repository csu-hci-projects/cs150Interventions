package edu.colostate.csedu.util.canvas

import edu.colostate.csedu.util.file.parseCSVLine
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.stream.Collectors


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


/**
 * Loads a canvas file storing both student info (the first 5 columns), and the student
 * data for later parsing.
 */
fun  loadCanvasCsv(filename: String) :  List<CanvasData> {

    try {
        val lines = File(filename).bufferedReader().readLines()

        var headers =  lines[0].parseCSVLine()
        headers = headers.subList(5, headers.size) // ignore the Canvas first 5 headers

        return lines.stream().skip(1).map { s -> convertLineToStudentData(s,
                headers)}.collect(Collectors.toList()).filterNotNull()
    }catch(e :  Exception) {
        print(e.message)
    }
    return mutableListOf()  // just return an empty list
}

/**
 * Loads an email dump from campus navigate. CSUID,Email
 */
fun loadEmailCsv(filename:String) : Map<String, String> {
    val map = mutableMapOf<String, String>()
    try {
        val lines = File(filename).bufferedReader().readLines()
        for(line in lines.stream().skip(1)){ // probably easier way to do this with stream.collect
            if(line.isNotEmpty()) {
                val split = line.split(",")
                map[split[0]] = split[1]
            }
        }
    }catch(e :  Exception) {
        print(e.message)
    }

    return map


}