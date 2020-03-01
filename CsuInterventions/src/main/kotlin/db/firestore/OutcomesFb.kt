package edu.colostate.csedu.db.firestore

import edu.colostate.csedu.db.Outcomes
import edu.colostate.csedu.db.entity.Outcome
import edu.colostate.csedu.db.entity.Student

class OutcomesFb(var db: Firebase) : Outcomes{

    override fun set(outcome: Outcome) {
        db.setDocument(OUTCOMES_TABLE_NAME, outcome.id, outcome.toMap())
    }

    override fun add(outcome: Outcome) {
        db.addDocument(OUTCOMES_TABLE_NAME, outcome.toMap())
    }

    override fun getAll(): Map<String, Outcome> {
        val collection = db.getCollection(OUTCOMES_TABLE_NAME)
        val list = mutableMapOf<String, Outcome>()
        for (document in collection ) {
            list[document.id]  = document.toObject(Outcome::class.java)
            list[document.id]?.id = document.id
        }
        return list
    }


    companion object {
        private const val OUTCOMES_TABLE_NAME = "outcomes"


    }
}