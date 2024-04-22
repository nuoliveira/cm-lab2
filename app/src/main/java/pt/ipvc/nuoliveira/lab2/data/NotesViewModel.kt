package pt.ipvc.nuoliveira.lab2.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Date

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    var note: Note = Note(0, "")
    val allNotes: LiveData<List<Note>>
    private val repository: NotesRepository

    init {
        val notesDao = AppDatabase.getInstance(application).noteDao()
        repository = NotesRepository(notesDao)
        allNotes = repository.readAllNotes()
    }

    fun createNote() {
        viewModelScope.launch(Dispatchers.IO) {
            note.id = 0
            repository.createNote(note)
        }
    }

    fun updateNote() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun deleteNote() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }
}
