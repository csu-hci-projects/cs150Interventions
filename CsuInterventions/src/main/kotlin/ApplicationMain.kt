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
package edu.colostate.csedu

import edu.colostate.csedu.controller.ClickThroughController
import edu.colostate.csedu.controller.InterventionController
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.response.respondText
import io.ktor.routing.*
import kotlinx.html.*

// Entry Point of the application as defined in resources/application.conf.
// @see https://ktor.io/servers/configuration.html#hocon-file
fun Application.main() {
    // This adds Date and Server headers to each response, and allows custom additional headers
    install(DefaultHeaders)
    // This uses use the logger to log every call (request/response)
   // install(CallLogging)



    // Registers routes
    routing {
        // Here we use a DSL for building HTML on the route "/"
        // @see https://github.com/Kotlin/kotlinx.html
        get("/") {
            call.respondHtml {
                head {
                    title { +"You should not be here" }
                }
                body {
                    p {
                        +"You should not be here"
                    }
                }
            }
        }

        get("/{url}") {
            val url = call.parameters["url"] ?: ""
            val redirectUrl = ClickThroughController.redirectRoute(url)
            call.respondRedirect(redirectUrl, permanent = false)
        }

        // leaving code as a  reminder for future API methods
        //call.respondText(response, contentType = ContentType.Application.Json)


    }
}