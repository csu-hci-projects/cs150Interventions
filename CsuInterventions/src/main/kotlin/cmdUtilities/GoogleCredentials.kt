import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.gmail.GmailScopes
import com.google.api.services.sheets.v4.SheetsScopes
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

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
object GoogleCredentials {
    val JSON_FACTORY = JacksonFactory.getDefaultInstance()
    private val TOKENS_DIRECTORY_PATH = "tokens"
    private val CREDENTIALS_FILE_PATH = "/credentials.json"

    private val SCOPES = listOf(GmailScopes.GMAIL_LABELS, GmailScopes.GMAIL_SEND,
            GmailScopes.GMAIL_COMPOSE, GmailScopes.MAIL_GOOGLE_COM,
            SheetsScopes.SPREADSHEETS_READONLY)

    @Throws(IOException::class)
    fun getCredentials(HTTP_TRANSPORT: NetHttpTransport): Credential {
        // Load client secrets.
        val `in` = this::class.java.getResourceAsStream(CREDENTIALS_FILE_PATH)
                ?: throw FileNotFoundException("Resource not found: $CREDENTIALS_FILE_PATH")
        val clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(`in`))

        // Build flow and trigger user authorization request.
        val flow = GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build()
        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }
}