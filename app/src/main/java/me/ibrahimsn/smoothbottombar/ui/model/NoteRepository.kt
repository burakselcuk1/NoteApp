package me.ibrahimsn.smoothbottombar.ui.model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class NoteRepository(private val noteDao : NoteDao) {

    val readAllData: LiveData<List<notes>> = noteDao.readAllData()
    val readOneData: LiveData<notes> = noteDao.readOneData()

    suspend fun addNote(note: notes){
        noteDao.addNote(note)
    }

    suspend fun deleteUser(note: notes){
        noteDao.deleteUser(note)
    }

    suspend fun update(note: notes){
        noteDao.update(note)
    }



}