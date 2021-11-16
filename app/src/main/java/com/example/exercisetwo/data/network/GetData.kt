package com.example.exercisetwo.data.network

import android.database.Observable
import com.example.exercisetwo.data.model.NewsRetroResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*
const val BASE_URL = "https://dl.dropboxusercontent.com"
interface GetData {
    //Describe the request type and the relative URL//



    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getData() : io.reactivex.Observable<NewsRetroResponse>

}
