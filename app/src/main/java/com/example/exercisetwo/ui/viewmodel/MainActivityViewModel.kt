package com.example.exercisetwo.ui.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.*
import com.example.exercisetwo.data.db.RowsDatabase
import com.example.exercisetwo.data.model.NewsRetroResponse
import com.example.exercisetwo.data.model.Rows
import com.example.exercisetwo.data.network.ApiClient
import com.example.exercisetwo.data.repository.MainActivityRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("StaticFieldLeak")
class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Rows>>
     val repository: MainActivityRepository
    val newsResponse = MutableLiveData<NewsRetroResponse>()
    private var mCompositeDisposable: CompositeDisposable? = null

    init {
        val rowsDao = RowsDatabase.getDatabase(application).rowsDao()
        repository = MainActivityRepository(rowsDao)
        readAllData = repository.readAllData
    }
    fun addRows(rows: List<Rows>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRows(rows)
        }
    }

    fun getNewsDatas(): LiveData<NewsRetroResponse>? {
         getServiceApi()
        return newsResponse
    }
   private fun getServiceApi(): CompositeDisposable? {

        mCompositeDisposable = CompositeDisposable()
           repository.getNewsDatas()
               ?.observeOn(AndroidSchedulers.mainThread())
               ?.subscribeOn(Schedulers.io())?.let {

                   mCompositeDisposable?.add(
                       it
                           .subscribe(::handleResponse,::handleError)
                   )
               }

        return mCompositeDisposable
    }




    private fun handleResponse(androidList: NewsRetroResponse) {
        newsResponse.value = androidList

    }

    private fun handleError(error: Throwable) {

        Log.d(ContentValues.TAG, error.localizedMessage)

    }


}

