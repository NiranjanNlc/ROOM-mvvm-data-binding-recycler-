package com.example.coroutine.modal.dao
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.coroutine.modal.data.Girls
import kotlinx.coroutines.flow.Flow


@Dao
interface GirlsDao {
    @Insert(onConflict = REPLACE)
    fun save(girls:Girls)

    @Update
    fun update(girls:Girls)

    @Query("SELECT * FROM  girls ORDER BY name ASC")
    fun getAlphabetizedWords(): LiveData<List<Girls>>

}