package hw2_2

import hw2_2.NoteService.lastCommentId
import hw2_2.NoteService.noteComments
import hw2_2.NoteService.notes

//интерфейс для службы
interface CrudService<E> {
    fun add(entity: E): E
    fun delete(id: Long)
    fun edit(id: Long,  text: String)
    fun read(): List<E>
    fun getById(id: Long): E

}

//создаем синглтон Заметки с методами. Унаследуется от интерфейса КРУД
object NoteService : CrudService<Note> {
    val notes = mutableListOf<Note>()
    val noteComments = mutableListOf<NoteComment>()
    var lastNoteId: Long = 0
    var lastCommentId: Long = 0

    class NotFoundException(message: String = "Запись не найдена") : RuntimeException(message)

    override fun add(entity: Note): Note {
        entity.id = ++lastNoteId
        notes.add(entity)
        return notes.last()
    }

    override fun delete(id: Long) {
        //пробегаемся сначала по комментариям к заметке. Если находим, исправляем параметр - удалено
        for (comment in noteComments){
            if (id == comment.noteId){
                comment.isDeleted = true
            }
        }
        //теперь пробегаемся по заметкам. Физически удаляем найденный
        for (note in notes) {
            if (id == note.id) {
                notes.remove(note)
                return
            }
        }
        //если не найден - кидаем эксепшн
        throw NotFoundException()

    }

    //редактируем заметку по айди, с параметром нового текста
    override fun edit(id: Long,  text: String) {
        for (note in notes) {
            if (id == note.id) {
                note.text = text
                return
            }
        }
        throw NotFoundException()
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

//добавляем комментарии к заметке:

object NoteCommentService:CrudService<NoteComment> {
    class NotFoundCommentException(message: String = "Комментарий не найден") : RuntimeException(message)

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
        throw NotFoundCommentException()
    }

    override fun read(): List<NoteComment> {
        //здесь создаем новый массив неудаленных заметок:
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
        throw NotFoundCommentException()
    }

    fun restore(id: Long) {
        for (comment in noteComments){
            if (id == comment.id){
                comment.isDeleted = false
                return
            }
        }
        throw NotFoundCommentException()
    }
}