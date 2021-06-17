package com.example.coroutine.modal.Repository

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.coroutine.modal.dao.GirlsDao
import com.example.coroutine.modal.data.Girls

class GirlsRepository(private val girlsDao: GirlsDao) {

    val allGirlss: LiveData<List<Girls>> = girlsDao.getAlphabetizedWords()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(girls: Girls)
    {
        println(" inserted the girls  ")
        girlsDao.save(girls)
        println(allGirlss.getValue())
    }

}