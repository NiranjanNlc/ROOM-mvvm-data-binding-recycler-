package com.example.coroutine

import android.app.Application
import android.content.Context
import com.example.coroutine.modal.Repository.GirlsRepository
import com.example.coroutine.modal.database.GirlssDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class GirlsApplication(context: Context)
{
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { GirlssDataBase.getDatabase(context, applicationScope) }
    val repository by lazy { GirlsRepository(database.girldDao()) }
}