package com.example.exercisetwo.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercisetwo.data.model.NewsModel
import com.example.exercisetwo.data.model.NewsRetroResponse
import com.example.exercisetwo.data.repository.MainActivityRepository
import java.util.ArrayList

@SuppressLint("StaticFieldLeak")
 class MainActivityViewModel(mainActivityRepository: MainActivityRepository) : ViewModel() {




    var servicesLiveData: MutableLiveData<NewsRetroResponse>? = null



    fun getUser() : LiveData<NewsRetroResponse>? {
        servicesLiveData = MainActivityRepository().getServiceApiCall()
        return servicesLiveData
    }
    fun setNewValue(newValue: NewsRetroResponse) {
        servicesLiveData?.value =newValue
    }



}
