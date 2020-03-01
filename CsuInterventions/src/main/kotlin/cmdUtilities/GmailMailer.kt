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

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.googleapis.json.GoogleJsonResponseException
import com.google.api.client.util.Base64
import com.google.api.services.gmail.Gmail
import com.google.api.services.gmail.model.Message
import javax.mail.MessagingException
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Handler
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class GmailMailer {
    private val APPLICATION_NAME = "Gmail API Mass Mailer"
    private val FROM = "lionelle@rams.colostate.edu"

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */




    fun sendMail(to : String, subject : String, body : String) {


        try {
            val message = createMessageWithEmail(createEmail(to=to, from=FROM, subject = subject, bodyText = body))

            TimeUnit.SECONDS.sleep(20);
                val msg = getService().users().messages().send("me", message).execute()
                println("Message id: " + msg.getId());



        } catch (ex : Exception) {
            println("Error for email to: $to with message ${ex.message}")
        }

    }

    @Throws(MessagingException::class)
    fun createEmail(to: String,
                    from: String,
                    subject: String,
                    bodyText: String): MimeMessage {
        val props = Properties()
        val session = Session.getDefaultInstance(props, null)

        val email = MimeMessage(session)

        email.setFrom(InternetAddress(from))
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                InternetAddress(to))
        email.subject = subject
        email.setContent(bodyText, "text/html")
        return email
    }

    @Throws(MessagingException::class, IOException::class)
    fun createMessageWithEmail(email: MimeMessage): Message {
        val baos = ByteArrayOutputStream()
        email.writeTo(baos)
        val encodedEmail = Base64.encodeBase64URLSafeString(baos.toByteArray())
        val message = Message()
        message.raw = encodedEmail
        return message
    }


    fun getService() : Gmail {
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        return  Gmail.Builder(httpTransport, GoogleCredentials.JSON_FACTORY, GoogleCredentials.getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build()
    }

    fun test() {
        // Build a new authorized API client service.
        val service = getService()

        // Print the labels in the user's account.
        val user = "me"
        val listResponse = service.users().labels().list(user).execute()
        val labels = listResponse.labels
        if (labels.isEmpty()) {
            println("No labels found.")
        } else {
            println("Labels:")
            for (label in labels) {
                System.out.printf("- %s\n", label.name)
            }
        }
    }
}


