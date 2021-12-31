package me.ibrahimsn.smoothbottombar.ui.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
       suspend fun addNote(note: notes)

        @Query("SELECT * FROM note_table ORDER BY id ASC")
        fun readAllData():LiveData<List<notes>>

        @Delete
        suspend fun deleteUser(note: notes)

        @Update
        suspend fun update(note: notes)


        // One Data
         @Query("SELECT * FROM note_table ORDER BY id DESC LIMIT 1")
         fun readOneData():LiveData<notes>


}