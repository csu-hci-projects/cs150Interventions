package edu.colostate.csedu.db.firestore

import edu.colostate.csedu.db.Students
import edu.colostate.csedu.db.entity.Course
import edu.colostate.csedu.db.entity.Student

class StudentsFb(var db: Firebase) : Students {
    override fun get(studentId: String): Student? {
        val document = db.getDocument(STUDENTS_TABLE_NAME, studentId)
        document?.let {
            val student = document.toObject(Student::class.java)
            student?.id = document.id
            return student
        }
        return null
    }


    override fun getAll(): Map<String, Student> {
        val collection = db.getCollection(STUDENTS_TABLE_NAME)
        val list = mutableMapOf<String, Student>()
        for (document in collection ) {
            list[document.id] = document.toObject(Student::class.java)
            list[document.id]?.id = document.id
        }
        return list
    }

    override fun add(student: Student) : String {
       return db.addDocument(STUDENTS_TABLE_NAME, student.toMap()).id
    }
    override fun set(id: String, student: Student) {
        db.setDocument(STUDENTS_TABLE_NAME, id, student.toMap())
    }


    companion object {
        private const val STUDENTS_TABLE_NAME = "students"

    }
}