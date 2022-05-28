package hw2_2

import hw2_2.NoteService.lastNoteId
import hw2_2.NoteService.noteComments
import hw2_2.NoteService.notes
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @org.junit.Test
    fun addChecking() {
        println("addChecking")
        notes.clear()
        lastNoteId = 0

        val note = Note(title = "Заметка 1", text = "бла бла")
        val note2 = Note(title = "Заметка 2", text = "ля ля ля")
        val note3 = Note(title = "Заметка 3", text = "улю лю")
        NoteService.add(note)
        NoteService.add(note2)
        NoteService.add(note3)

        val result = note.id
        assertTrue(result > 0)


    }

    @org.junit.Test
    fun delete() {
        println("deleteCheck")
        notes.clear()
        lastNoteId = 0
        val note = Note(title = "Заметка 1", text = "бла бла")
        val note2 = Note(title = "Заметка 2", text = "ля ля ля")
        val note3 = Note(title = "Заметка 3", text = "улю лю")
        NoteService.add(note)
        NoteService.add(note2)
        NoteService.add(note3)
        println(notes)
        NoteService.delete(1)

        val result = notes.size
        println("размер массива: $result")
        assertTrue(result == 2)

    }

    @Test(expected = NoteService.NotFoundException ::class)
    fun shouldThrow() {
        // здесь код с вызовом функции, которая должна выкинуть PostNotFoundException
        notes.clear()
        lastNoteId = 0
        val note = Note(title = "Заметка 1", text = "бла бла")
        NoteService.add(note)
       NoteService.delete((0))
    }

    @org.junit.Test
    fun editCheck() {
        println("editCheck")
        notes.clear()
        lastNoteId = 0
        val note = Note(title = "Заметка 1", text = "бла бла")
        val note2 = Note(title = "Заметка 2", text = "ля ля ля")
        val note3 = Note(title = "Заметка 3", text = "улю лю")
        NoteService.add(note)
        NoteService.add(note2)
        NoteService.add(note3)
        NoteService.edit(1, "EDITED")

        var result = note.text
        println(result)
        println(notes)
        assertTrue( result == "EDITED")
    }

    @Test(expected = NoteService.NotFoundException ::class)
    fun shouldThrowEditException() {
         notes.clear()
        lastNoteId = 0
        val note = Note(title = "Заметка 1", text = "бла бла")
        NoteService.add(note)
        println(note)
        NoteService.edit(1,"lalala")
    }

    @org.junit.Test
    fun readCheck() {
        lastNoteId = 0
        notes.clear()
        println("readCheck")
        val note = Note(title = "Заметка 1", text = "бла бла")
        val note2 = Note(title = "Заметка 2", text = "ля ля ля")
        NoteService.add(note)
        NoteService.add(note2)
        val result = notes.size
        assertTrue(result == 2)

    }

    @org.junit.Test
    fun getByIdCheck() {
        lastNoteId = 0
        notes.clear()
        val note = Note(title = "Заметка 1", text = "бла бла")
        val note2 = Note(title = "Заметка 2", text = "ля ля ля")
        NoteService.add(note)
        NoteService.add(note2)
        val result = NoteService.getById(2)
        assertTrue(result == note2)

    }

    @org.junit.Test
    fun addCommentCheck(){
        lastNoteId = 0
        notes.clear()
        noteComments.clear()
        val note = Note(title = "Заметка 1", text = "бла бла")
        val note2 = Note(title = "Заметка 2", text = "ля ля ля")
        NoteService.add(note)
        NoteService.add(note2)
        val noteComment = NoteComment(noteId = 1, text="Комментарий к заметке 1")
        NoteCommentService.add(noteComment)
        val result = noteComments.size
        assertTrue(result != 0)
    }

    @org.junit.Test
    fun deleteCommentCheck(){
        lastNoteId = 0
        notes.clear()
        noteComments.clear()
        val note = Note(title = "Заметка 1", text = "бла бла")
        val note2 = Note(title = "Заметка 2", text = "ля ля ля")
        NoteService.add(note)
        val noteComment = NoteComment(noteId = 1, text="Комментарий к заметке 1")
        NoteCommentService.add(noteComment)
        NoteCommentService.delete(1)
        val result = noteComment.isDeleted
        assertTrue(result)
    }

    @org.junit.Test
    fun restoreCommentCheck(){
        lastNoteId = 0
        notes.clear()
        noteComments.clear()
        val note = Note(title = "Заметка 1", text = "бла бла")
        val note2 = Note(title = "Заметка 2", text = "ля ля ля")
        NoteService.add(note)
        val noteComment = NoteComment(noteId = 1, text="Комментарий к заметке 1")
        NoteCommentService.add(noteComment)
        NoteCommentService.delete(1)
        NoteCommentService.restore(1)
        val result = noteComment.isDeleted
        assertTrue(!result)
    }

    @Test(expected = NoteCommentService.NotFoundCommentException ::class)
    fun shouldThrowRestoreWrong(){
        lastNoteId = 0
        notes.clear()
        noteComments.clear()
        val note = Note(title = "Заметка 1", text = "бла бла")
        val note2 = Note(title = "Заметка 2", text = "ля ля ля")
        NoteService.add(note)
        val noteComment = NoteComment(noteId = 1, text="Комментарий к заметке 1")
        NoteCommentService.add(noteComment)
        NoteCommentService.delete(1)
        NoteCommentService.restore(0)

    }
}