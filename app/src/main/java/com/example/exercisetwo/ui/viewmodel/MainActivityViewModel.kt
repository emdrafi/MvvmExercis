package com.example.exercisetwo.ui.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exercisetwo.data.model.NewsModel
import com.example.exercisetwo.ui.view.activity.MainActivity
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.util.ArrayList

@SuppressLint("StaticFieldLeak")
 class MainActivityViewModel() : ViewModel() {



    var arrayListMutableLiveData=MutableLiveData<ArrayList<NewsModel>>()
    var arrayList=ArrayList<NewsModel>()
    fun getArrayList():MutableLiveData<ArrayList<NewsModel>>
    {


        arrayListMutableLiveData.value=arrayList
        return arrayListMutableLiveData
    }



}
