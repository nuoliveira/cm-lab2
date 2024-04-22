package pt.ipvc.nuoliveira.lab2.data

import androidx.lifecycle.LiveData

class NotesRepository(private val dao: NotesDao) {
    fun readAllNotes(): LiveData<List<Note>> {
        return dao.readAllNotes()
    }

    suspend fun createNote(note: Note) {
        dao.createNote(note)
    }

    suspend fun updateNote(note: Note) {
        dao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}
