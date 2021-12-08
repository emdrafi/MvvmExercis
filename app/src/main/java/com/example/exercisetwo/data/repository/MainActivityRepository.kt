package com.example.exercisetwo.data.repository
import androidx.lifecycle.LiveData
import com.example.exercisetwo.data.db.RowsDao
import com.example.exercisetwo.data.model.NewsRetroResponse
import com.example.exercisetwo.data.model.Rows
import com.example.exercisetwo.data.network.ApiClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivityRepository(private val rowsDao: RowsDao) {
    val readAllData: LiveData<List<Rows>> = rowsDao.readAllData()
    suspend fun addRows(rows: List<Rows>) {
        rowsDao.addRows(rows)
    }

     fun getNewsDatas(): Observable<NewsRetroResponse>? {
        return ApiClient.getRetrofitData()?.getData()

    }
}