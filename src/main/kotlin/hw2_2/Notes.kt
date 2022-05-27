package hw2_2

import hw2_2.NoteService.noteComments
import hw2_2.NoteService.notes

open class Note(
    var id: Long = 0,
    var title: String ="",
    var text: String=""
){
    override fun toString(): String {
        return "Note(id=$id, title='$title', text='$text')"
    }
}

class NoteComment(var noteId: Long = 0, var isDeleted: Boolean = false,text: String):Note(text = text){
    override fun toString(): String {
        return "NoteComment(noteId=$noteId, isDeleted=$isDeleted id=$id, text = $text)"
    }
}
