package me.ibrahimsn.smoothbottombar.ui.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [notes::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun dao(): NoteDao

    companion object{

        @Volatile
        private var INSTANCE : NoteDatabase?=null


        fun makeDatabase(context: Context):NoteDatabase{
            var temp = INSTANCE
            if (temp!=null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }


    }

}