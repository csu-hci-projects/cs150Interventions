package edu.colostate.csedu.controller

import edu.colostate.csedu.DB
import io.ktor.http.toHttpDateString
import org.slf4j.LoggerFactory
import java.time.LocalDateTime


private val logger = LoggerFactory.getLogger(ClickThroughController.javaClass)


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
object ClickThroughController {

    /**
     * increments the click, and returns the URL to be redirected
     */
    fun redirectRoute(url: String) : String {
        //test line http://localhost:8080/AxY6C
        //live test https://csuinterventions.appspot.com/AxY6C

        val click = DB.clicks.get(url)
        click?.let {
            click.accessed += LocalDateTime.now().toHttpDateString()
            DB.clicks.set(click)
            return click.url
        }
        return ""
    }


}