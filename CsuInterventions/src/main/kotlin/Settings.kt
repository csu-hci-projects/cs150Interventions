package edu.colostate.csedu

import edu.colostate.csedu.db.Database
import edu.colostate.csedu.db.firestore.Firebase


val SETTINGS = Settings()

val DB = Firebase() as Database

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
class Settings {
    val HOST: String = "https://csuinterventions.appspot.com"
    val TEMPLATE_PATH: String = "/templates/"  // path relative to the resources folder. Don't forget the two slashes
    val FIRE_BASE_CREDENTIALS = "/csuinterventions-c7df0cc16894.json"

    val GOOGLE_API_CREDENTIALS =  "/client_secret_253542475069-bsidffnfetth4e7aecijg54tbt1aq7bp.apps.googleusercontent.com.json"

    val SERVICE_ACCOUNT = "gmail-460@csuinterventions.iam.gserviceaccount.com"
    val SERVICE_ACCOUNT_JSON = "/CSUInterventions-f83c8b83b4dd.json"


}