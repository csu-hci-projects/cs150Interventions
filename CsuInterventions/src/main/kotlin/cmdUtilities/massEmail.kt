import com.google.gson.Gson
import java.io.InputStreamReader

object massEmailer {

}

fun main(args: Array<String>) {

    val serviceAccount = InputStreamReader(massEmailer.javaClass.getResourceAsStream("/${args.getOrNull(0) ?: "emailtest.json"}"))

    val gson = Gson()
    val list = gson.fromJson(serviceAccount, Any::class.java)

    val mailer = GmailMailer()

    for(message in list as List<Map<String, String>>) { // commented out so emails aren't accidentally sent during debug - don't trust myself :p
        // (uncomment) mailer.sendMail(message["to"]!!, message["subject"]!!, message["body"]!!)
    }



}
