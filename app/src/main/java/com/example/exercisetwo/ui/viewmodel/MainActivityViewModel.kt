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
    val newsSetterAndGetter = MutableLiveData<NewsRetroResponse>()

    private var mCompositeDisposable: CompositeDisposable? = null


    private var servicesLiveData: MutableLiveData<NewsRetroResponse>? = null

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


    fun getUser(): LiveData<NewsRetroResponse>? {
        mCompositeDisposable = CompositeDisposable()
        getServiceApi()


        servicesLiveData = getServiceApiCall()
        return servicesLiveData
    }

   private fun getServiceApiCall(): MutableLiveData<NewsRetroResponse> {

        return newsSetterAndGetter

    }

   private fun getServiceApi(): CompositeDisposable? {
        mCompositeDisposable = CompositeDisposable()

        ApiClient.getRetrofitData()?.getData()
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())?.let {
                mCompositeDisposable?.add(
                    it
                        .subscribe(this::handleResponse, this::handleError)
                )
            }
        return mCompositeDisposable
    }

   private fun handleResponse(androidList: NewsRetroResponse) {
        newsSetterAndGetter.value = androidList

    }

    private fun handleError(error: Throwable) {

        Log.d(ContentValues.TAG, error.localizedMessage)

    }


}

