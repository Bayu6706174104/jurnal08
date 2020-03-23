package com.d3if4104.diariku.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_diari_table")
data class Diari(
    @PrimaryKey(autoGenerate = true)
    var diariId: Long = 0L,

    @ColumnInfo(name = "isi_diari")
    var diari: String = "",

    @ColumnInfo(name = "Tanggal")
    val tanggalDiari: Long = System.currentTimeMillis()


)