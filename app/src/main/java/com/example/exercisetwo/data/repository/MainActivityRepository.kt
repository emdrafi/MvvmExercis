package com.example.exercisetwo.data.repository

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exercisetwo.data.db.RowsDao
import com.example.exercisetwo.data.model.NewsRetroResponse
import com.example.exercisetwo.data.model.Rows
import com.example.exercisetwo.data.network.ApiClient
import com.example.exercisetwo.data.network.GetData

import com.example.exercisetwo.ui.adapter.NewsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityRepository(private val rowsDao: RowsDao) {
    val newsSetterAndGetter = MutableLiveData<NewsRetroResponse>()
    fun getServiceApiCall(): MutableLiveData<NewsRetroResponse> {

        return newsSetterAndGetter

    }
    val readAllData : LiveData<List<Rows>> = rowsDao.readAllData()

    suspend fun addRows(rows:  List<Rows>) {
        rowsDao.addRows(rows)
    }



}