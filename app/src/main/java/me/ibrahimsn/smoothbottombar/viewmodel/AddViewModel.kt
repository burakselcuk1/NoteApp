package me.ibrahimsn.smoothbottombar.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ibrahimsn.smoothbottombar.ui.model.NoteDatabase
import me.ibrahimsn.smoothbottombar.ui.model.NoteRepository
import me.ibrahimsn.smoothbottombar.ui.model.notes

class AddViewModel(application: Application): AndroidViewModel(application) {


    val readAllData: LiveData<List<notes>>
    private val repository: NoteRepository
    val readOneData: LiveData<notes>

    init {
        val noteDao = NoteDatabase.makeDatabase(application).dao()
        repository = NoteRepository(noteDao)
        readAllData = repository.readAllData
        readOneData = repository.readOneData
    }

    fun addNote(note: notes){
        viewModelScope.launch(Dispatchers.IO ) {
            repository.addNote(note)
        }
    }

    fun deleteNote(note: notes){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteUser(note)
        }
    }

    fun updateNote(note: notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(note)
        }
    }







}