package hw2_2

import hw2_2.NoteService.lastCommentId
import hw2_2.NoteService.noteComments
import hw2_2.NoteService.notes

interface CrudService<E> {
    fun add(entity: E): E
    fun delete(id: Long)
    fun edit(id: Long,  text: String)
    fun read(): List<E>
    fun getById(id: Long): E

}

class NotFoundException(message: String = "Запись не найдена") : RuntimeException(message)

object NoteService : CrudService<Note> {
    val notes = mutableListOf<Note>()
    val noteComments = mutableListOf<NoteComment>()
    var lastNoteId: Long = 0
    var lastCommentId: Long = 0
    override fun add(entity: Note): Note {
        entity.id = ++lastNoteId
        notes.add(entity)
        return notes.last()
    }

    override fun delete(id: Long) {
        for (note in notes) {
            if (id == note.id) {
                notes.remove(note)
            }
        }
        for (comment in noteComments){
            if (id == comment.noteId){
                comment.isDeleted = true
            }
        }
    }

    override fun edit(id: Long,  text: String) {
        for (note in notes) {
            if (id == note.id) {
                note.text = text
            }
        }
    }

    override fun read(): List<Note> {

        return notes
    }

    override fun getById(id: Long): Note {
       for (note in notes){
           if (id == note.id){
               return note
           }
       }
        throw NotFoundException()
    }

}

object NoteCommentService:CrudService<NoteComment> {
    override fun add(entity: NoteComment): NoteComment {
        entity.id = ++ lastCommentId

        for (note in notes){
            if (entity.noteId == note.id){
                noteComments.add(entity)
            }
        }
        return noteComments.last()
    }

    override fun delete(id: Long) {
        for (comment in noteComments){
            if (id == comment.id) {
                comment.isDeleted = true
            }
        }
    }

    override fun edit(id: Long, text: String) {

        for (comment in noteComments){
            if (id == comment.id){
                comment.text = text
                return
            }
        }
        throw NotFoundException()
    }

    override fun read(): List<NoteComment> {
        val commentsToShow = mutableListOf<NoteComment>()
        for (comment in noteComments){
            if (!comment.isDeleted){
                commentsToShow.add(comment)
            }
        }
        return commentsToShow
    }

    override fun getById(id: Long): NoteComment {
        for (comment in noteComments){
            if (id == comment.id){
                return comment
            }
        }
        throw NotFoundException()
    }

    fun restore(id: Long) {
        for (comment in noteComments){
            if (id == comment.id){
                comment.isDeleted = false
                return
            }
        }
        throw NotFoundException()
    }
}