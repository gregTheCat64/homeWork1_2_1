package hw2_2

import hw2_2.NoteService.lastNoteId
import hw2_2.NoteService.notes
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @org.junit.Test
    fun addChecking() {
        println("addChecking")
        val note = Note(title = "Заметка 1", text = "бла бла")
        val note2 = Note(title = "Заметка 2", text = "ля ля ля")
        val note3 = Note(title = "Заметка 3", text = "улю лю")
        NoteService.add(note)
        NoteService.add(note2)
        NoteService.add(note3)

        val result = note.id
        assertTrue(result > 0)
        notes.clear()

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
        notes.clear()

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
        val result = NoteService.read()
        assertTrue(result != null)

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
}