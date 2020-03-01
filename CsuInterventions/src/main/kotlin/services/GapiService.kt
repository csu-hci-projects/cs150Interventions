package edu.colostate.csedu.services


import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.util.store.FileDataStoreFactory
import edu.colostate.csedu.SETTINGS

import java.io.FileNotFoundException
import java.io.InputStreamReader


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.DataStoreFactory
import com.google.api.client.util.store.MemoryDataStoreFactory
import com.google.api.services.gmail.Gmail
import com.google.api.services.gmail.GmailScopes

import java.io.IOException
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential
import com.google.auth.oauth2.GoogleCredentials


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

// gmail-460@csuinterventions.iam.gserviceaccount.com


class GapiService {
    private val APPLICATION_NAME = "Gmail API Java Quickstart"
    private val JSON_FACTORY = JacksonFactory.getDefaultInstance()
    private val TOKENS_DIRECTORY_PATH = "tokens"

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private val SCOPES = listOf(GmailScopes.GMAIL_LABELS)


    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    @Throws(IOException::class)
    private fun getCredentials(httpTransport: NetHttpTransport): AppIdentityCredential {

        // Load client secrets.
      /*val `in` = GapiService::class.java.getResourceAsStream(SETTINGS.GOOGLE_API_CREDENTIALS)
                ?: throw FileNotFoundException("Resource not found: ${SETTINGS.GOOGLE_API_CREDENTIALS}")
        val clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(`in`))
     // Build flow and trigger user authorization request.
        val flow = GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(MemoryDataStoreFactory.getDefaultInstance())
                .build()
        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")*/
        return AppIdentityCredential(SCOPES)



        //return GoogleCredential.fromStream(GapiService::class.java.getResourceAsStream(SETTINGS.SERVICE_ACCOUNT_JSON))
          //                         .createScoped(SCOPES)

    }

    fun getLables() : String {
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
/*
        val service = Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build()
*/


        val service = Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build()
        var str = ""
        // Print the labels in the user's account.
        val user = "lionelle@rams.colostate.edu"
        val listResponse = service.users().labels().list(user).execute()
        val labels = listResponse.labels
        if (labels.isEmpty()) {
            str += "No labels found."
        } else {
            str += "Labels:"
            for (label in labels) {
                str += "- ${label.name}\n"
            }
        }
        return str
    }

}
