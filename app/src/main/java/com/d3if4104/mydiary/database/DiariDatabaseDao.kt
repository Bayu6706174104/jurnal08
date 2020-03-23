package com.d3if4104.diariku.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DiariDatabaseDao {

    @Insert
    fun insert(diari : Diari)

    @Update
    fun update(diari : Diari)


    @Query("DELETE FROM daily_diari_table")
    fun clear()

    @Query("SELECT * FROM daily_diari_table")
    fun get(): LiveData<List<Diari>>

    @Query("SELECT * FROM daily_diari_table ORDER BY diariId DESC LIMIT 1")
    fun getDiari() :Diari


}