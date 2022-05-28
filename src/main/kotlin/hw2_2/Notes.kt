package hw2_2

import hw2_2.NoteService.noteComments
import hw2_2.NoteService.notes

//класс заметки
open class Note(
    var id: Long = 0,
    var title: String ="",
    var text: String=""
){
    override fun toString(): String {
        return "Note(id=$id, title='$title', text='$text')"
    }
}

//класс комментария к заметке. Унаследован от класса заметки
class NoteComment(var noteId: Long, var isDeleted: Boolean = false,text: String):Note(text = text){
    override fun toString(): String {
        return "NoteComment(noteId=$noteId, isDeleted=$isDeleted id=$id, text = $text)"
    }
}
