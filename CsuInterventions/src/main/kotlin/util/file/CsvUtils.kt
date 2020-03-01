package edu.colostate.csedu.util.file



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
 * Simple function for parsing  a CSV line with a look ahead to allow for quoted characters
 * Acts as an extension function to String, for a split style function that allows quoted escapes
 */
fun String.parseCSVLine(separator: Char = ',', escape:Char = '\"'): List<String> {
    val result = mutableListOf<String>()
    val builder = StringBuilder()
    var quotes = 0
    for (ch in this) {
        when {
            ch == escape -> {
                quotes++
                builder.append(ch)
            }
            (ch == '\n') || (ch == '\r') -> {  // do nothing

            }
            (ch == separator) && (quotes % 2 == 0) -> {
                result.add(builder.toString().replace(escape.toString(), "").trim())
                builder.clear()
            }
            else -> builder.append(ch)
        }
    }
    result.add(builder.toString().trim())
    return result
}