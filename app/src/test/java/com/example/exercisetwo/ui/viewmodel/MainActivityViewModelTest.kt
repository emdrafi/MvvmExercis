package com.example.exercisetwo.ui.viewmodel

import com.example.exercisetwo.data.model.NewsRetroResponse
import com.example.exercisetwo.data.model.Rows
import com.example.exercisetwo.data.repository.MainActivityRepository
import org.junit.Before
import org.junit.Test

class MainActivityViewModelTest{
    private lateinit var viewModel: MainActivityViewModel
    @Before
    fun setUp(){
        viewModel= MainActivityViewModel(MainActivityRepository())
    }
    @Test
    fun isLiveDataEmitting(){
        val data: java.util.ArrayList<Rows> = ArrayList()
      data.add(0, Rows("hai","des","img"))
        viewModel.setNewValue(NewsRetroResponse("Hai",data))
        viewModel.servicesLiveData?.let { assert(it.equals(data)) }


    }

}