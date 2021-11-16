package com.example.exercisetwo.ui.viewmodel

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercisetwo.data.model.NewsModel
import com.example.exercisetwo.data.model.NewsRetroResponse
import com.example.exercisetwo.data.network.ApiClient
import com.example.exercisetwo.data.repository.MainActivityRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

@SuppressLint("StaticFieldLeak")
class MainActivityViewModel : ViewModel() {
    val newsSetterAndGetter = MutableLiveData<NewsRetroResponse>()

    private var mCompositeDisposable: CompositeDisposable? = null


    //    private val BASE_URL = "https://dl.dropboxusercontent.com"


    var servicesLiveData: MutableLiveData<NewsRetroResponse>? = null


    fun getUser(): LiveData<NewsRetroResponse>? {
        mCompositeDisposable = CompositeDisposable()
        getServiceApi()


        servicesLiveData = getServiceApiCall()
        return servicesLiveData
    }

    fun getServiceApiCall(): MutableLiveData<NewsRetroResponse> {

        return newsSetterAndGetter

    }

    fun getServiceApi(): CompositeDisposable? {
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

    fun handleResponse(androidList: NewsRetroResponse) {
        newsSetterAndGetter.value = androidList


    }

    fun handleError(error: Throwable) {

        Log.d(ContentValues.TAG, error.localizedMessage)


    }


    fun setNewValue(newValue: NewsRetroResponse) {
        servicesLiveData?.value = newValue
    }


}

