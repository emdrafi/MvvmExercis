package com.example.exercisetwo.data.repository

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.exercisetwo.data.model.NewsRetroResponse
import com.example.exercisetwo.data.model.Rows
import com.example.exercisetwo.data.network.GetData
import com.example.exercisetwo.ui.adapter.NewsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityRepository {
    private var mCompositeDisposable: CompositeDisposable? = null
    private val BASE_URL = "https://dl.dropboxusercontent.com"
    val newsSetterAndGetter = MutableLiveData<NewsRetroResponse>()


    fun getServiceApiCall(): MutableLiveData<NewsRetroResponse> {
        mCompositeDisposable = CompositeDisposable()
        val requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GetData::class.java)
        mCompositeDisposable?.add(
            requestInterface.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        )
        return newsSetterAndGetter

    }


    private fun handleResponse(androidList: NewsRetroResponse) {


        newsSetterAndGetter.value = androidList


    }

    private fun handleError(error: Throwable) {

        Log.d(ContentValues.TAG, error.localizedMessage)


    }
}