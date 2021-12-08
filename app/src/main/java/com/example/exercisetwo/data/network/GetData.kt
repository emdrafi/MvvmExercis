package com.example.exercisetwo.data.network

import com.example.exercisetwo.data.model.NewsRetroResponse
import io.reactivex.Observable
import retrofit2.http.GET
const val BASE_URL = "https://dl.dropboxusercontent.com"
interface GetData {
    //Describe the request type and the relative URL//
    @GET("/s/2iodh4vg0eortkl/facts.json")
     fun getData() : Observable<NewsRetroResponse>

}
