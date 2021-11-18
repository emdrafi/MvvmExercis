package com.example.exercisetwo.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exercisetwo.data.model.Rows
@Dao
interface RowsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // if there is same user,then we will ignore it
    suspend fun addRows(rows:  List<Rows>) // suspend is added as we will use coroutine
    @Query("SELECT * FROM rows_table ORDER BY id")
     fun readAllData(): LiveData<List<Rows>>

}