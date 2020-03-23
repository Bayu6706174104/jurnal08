package com.d3if4104.mydiary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.d3if4104.diariku.Database.Diari
import com.d3if4104.diariku.Database.DiariDatabaseDao
import com.d3if4104.diariku.formatDiary
import kotlinx.coroutines.*

class DiariViewModel(
    val database: DiariDatabaseDao, application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

//    private var today = MutableLiveData<Diari?>()

    val diari = database.get()

    val hasil = Transformations.map(diari) { diarys ->
        formatDiary(diarys, application.resources)
//        recyclerview.addItemDecoration(DividerItemDecoration(application.applicationContext, LinearLayoutManager.VERTICAL))
    }

    fun onClickTambah(catat: String) {
        uiScope.launch {
            val diary = Diari(0, catat)
            insert(diary)
        }
    }

    fun onClickHapus() {
        uiScope.launch {
            clear()
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private suspend fun insert(diary: Diari) {
        withContext(Dispatchers.IO) {
            database.insert(diary)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}



