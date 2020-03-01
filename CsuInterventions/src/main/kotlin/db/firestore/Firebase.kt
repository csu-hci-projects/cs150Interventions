package edu.colostate.csedu.db.firestore

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.DocumentSnapshot
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.QueryDocumentSnapshot
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import edu.colostate.csedu.SETTINGS
import edu.colostate.csedu.db.*


import org.slf4j.LoggerFactory


private val logger = LoggerFactory.getLogger(Firebase::class.java)
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
class Firebase() : Database {

    var db : Firestore
    override lateinit var students: Students
    override lateinit var resources: Resources
    override lateinit var outcomes: Outcomes
    override lateinit var courses: Courses
    override lateinit var campaigns: Campaigns
    override lateinit var clicks: Clicks


    /*
    override fun addAssessment(student: Student, assessment: Assessment) {
        db.collection(assessmentListPath(student.courseId, student.id)).document(assessment.id).set(assessment.toMap()).get()
    }

    override fun addStudent(courseId: String, student: Student) {
        db.collection(studentListPath(courseId)).document(student.id).set(student.toMap()).get()
    }


    override fun getAssessment(student: Student, assessmentId: String): Assessment {
        val document = getDocument(assessmentDocPath(student.courseId, student.id, assessmentId))
        return Assessment.builder(document.id, document.data)
    }

    override fun getAllStudents(courseId: String): List<Student> {
       val collection = getCollection(studentListPath(courseId))
        val list = mutableListOf<Student>()
        for (document in collection ) {
            list += document.toObject(Student::class.java)
            list.last().id = document.id
            list.last().courseId = courseId
        }
        return list
    }


    override fun addClickToStudent(student: Student, click: Click): String {
        val document = db.collection(COURSES_TABLE_NAME).document(student.courseId)
                         .collection(STUDENTS_TABLE_NAME).document(student.id)
                         .collection(CLICKS_TABLE_NAME).add(click.toMap())
        return document.get().id
    }

    override fun getStudent(courseId: String, studentId: String): Student {
        val document = db.collection(COURSES_TABLE_NAME).document(courseId)
                        .collection(STUDENTS_TABLE_NAME).document(studentId).get().get()

        if(document.exists()) {
            val obj = document.toObject(Student::class.java)
            obj?.id = document.id
            obj?.courseId = courseId
            return obj!!
        }
        //else
        throw Exception("Can't find the student: $studentId in course $courseId")
    }

*/

    companion object {
        private const val COURSES_TABLE_NAME = "courses"
        private const val CLICKS_TABLE_NAME = "clicks"
        private const val ASSESSMENTS_TABLE_NAME = "assessments"



       // fun studentListPath(courseId: String) = "$COURSES_TABLE_NAME/$courseId/$STUDENTS_TABLE_NAME"

        //fun studentDocPath(courseId: String, studentId: String) = studentListPath(courseId) + "/$studentId"

//        fun assessmentListPath(courseId: String, studentId: String) = studentDocPath(courseId, studentId) + "/$ASSESSMENTS_TABLE_NAME"

  //      fun assessmentDocPath(courseId: String, studentId: String, assessmentId: String) = assessmentListPath(courseId, studentId) + "/$assessmentId"

    }


    internal fun getCollection(collection: String) : List<QueryDocumentSnapshot> {
        val query = db.collection(collection).get()
        val querySnapshot = query.get()
        return querySnapshot.documents
    }

    internal fun setDocument(collection: String, documentId: String, map : Map<String, Any>)  {
        db.collection(collection).document(documentId).set(map).get()
    }

    internal fun addDocument(collection: String,map : Map<String, Any>)  = db.collection(collection).add(map).get()


    internal fun getDocument(collection: String, documentId : String) : DocumentSnapshot? {
        val future = db.collection(collection).document(documentId).get()
        val document = future.get()
        if (document.exists()) {
            return document
        }
        //throw Exception("Can't find the document: $documentId")
        return null
    }


    init {
        val serviceAccount = this.javaClass.getResourceAsStream(SETTINGS.FIRE_BASE_CREDENTIALS)
        val credentials = GoogleCredentials.fromStream(serviceAccount)
        val options = FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build()
        FirebaseApp.initializeApp(options)
        this.db = FirestoreClient.getFirestore()

        this.students = StudentsFb(this)
        this.outcomes = OutcomesFb(this)
        this.resources = ResourcesFb(this)
        this.courses = CoursesFb(this)
        this.campaigns = CampaignsFb(this)
        this.clicks = ClicksFb(this)

    }




}