package com.d3if4104.diariku.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Diari::class],version = 1, exportSchema = false)
abstract class DiariDatabase : RoomDatabase() {

    abstract val diariDatabaseDao : DiariDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE : DiariDatabase? = null

        fun getInstance(context: Context): DiariDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DiariDatabase::class.java,
                        "diari_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}