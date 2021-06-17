package com.example.coroutine.modal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.coroutine.modal.dao.GirlsDao
import com.example.coroutine.modal.data.Girls
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Girls::class], version = 1)
abstract class GirlssDataBase : RoomDatabase() {
     abstract fun girldDao(): GirlsDao
    private class GirlsDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var girlDao = database.girldDao()
                    // Delete all content here.
                  //  girlDao.deleteAll()
                    // Add sample girls.
                    var girl = Girls(name ="Hello")
                    girlDao.save(girl)
                    println( girl.toString())

                }
            }
        }
    }
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: GirlssDataBase? = null

        fun getDatabase(context: Context,scope: CoroutineScope): GirlssDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database]
            println("You have acces over here ")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GirlssDataBase::class.java,
                    "girl_database"
                ).addCallback(GirlsDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

         
    }
}

