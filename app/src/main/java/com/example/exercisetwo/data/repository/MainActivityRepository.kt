package com.example.exercisetwo.data.repository
import androidx.lifecycle.LiveData
import com.example.exercisetwo.data.db.RowsDao
import com.example.exercisetwo.data.model.Rows


class MainActivityRepository(private val rowsDao: RowsDao) {

    val readAllData : LiveData<List<Rows>> = rowsDao.readAllData()

    suspend fun addRows(rows:  List<Rows>) {
        rowsDao.addRows(rows)
    }



}