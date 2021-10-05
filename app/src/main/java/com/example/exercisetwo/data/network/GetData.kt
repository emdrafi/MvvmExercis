package com.example.exercisetwo.data.network

import android.database.Observable
import com.example.exercisetwo.data.model.NewsRetroResponse
import retrofit2.http.GET
import java.util.*

interface GetData {
    //Describe the request type and the relative URL//



    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getData() : io.reactivex.Observable<NewsRetroResponse>

}