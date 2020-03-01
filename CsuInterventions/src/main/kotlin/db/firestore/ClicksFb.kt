package edu.colostate.csedu.db.firestore

import edu.colostate.csedu.db.Clicks
import edu.colostate.csedu.db.entity.Click
import edu.colostate.csedu.db.entity.Course

/**
 *
 *
 *
 * @author Albert Lionelle <br>
 *         lionelle@colostate.edu <br>
 *         Computer Science Department <br>
 *         Colorado State University
 * @version  202010
 */
class ClicksFb(val db:Firebase) : Clicks {
    override fun set(click: Click) {
        db.setDocument(CLICKS_TABLE_NAME, click.id, click.toMap())
    }

    override fun get(clickId: String): Click? {
        val document = db.getDocument(CLICKS_TABLE_NAME, clickId)
        document?.let {
            val click = document.toObject(Click::class.java)
            click?.id = document.id
            return click
        }
        return null
    }

    companion object {
        const val CLICKS_TABLE_NAME = "clicks"
    }
}