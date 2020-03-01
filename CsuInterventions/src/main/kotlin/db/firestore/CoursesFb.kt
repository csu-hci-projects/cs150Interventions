package edu.colostate.csedu.db.firestore

import edu.colostate.csedu.db.Courses
import edu.colostate.csedu.db.entity.Course


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
class CoursesFb(val db: Firebase) : Courses {

    override fun get(courseId: String) : Course? {
        val document = db.getDocument(COURSES_TABLE_NAME,
                        courseId)
        document?.let {
            val course = document.toObject(Course::class.java)
            course?.id = document.id
            return course
        }
        return null
    }

    override fun set(course: Course) {
        db.setDocument(COURSES_TABLE_NAME, course.id, course.toMap())
    }


    companion object {
        internal const val COURSES_TABLE_NAME = "courses"
    }
}