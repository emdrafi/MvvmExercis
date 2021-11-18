package com.example.exercisetwo.ui.view.activity


import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercisetwo.R
import com.example.exercisetwo.data.model.NewsModel
import com.example.exercisetwo.data.model.NewsRetroResponse
import com.example.exercisetwo.data.model.Rows
import com.example.exercisetwo.data.network.GetData
import com.example.exercisetwo.databinding.ActivityMainBinding
import com.example.exercisetwo.ui.adapter.NewsAdapter
import com.example.exercisetwo.ui.viewmodel.MainActivityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        manager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        getRecycler()

    }

    private fun getRecycler() {
        viewModel.getUser()?.observe(this, Observer {

            viewModel.addRows(it.rows)

        })
        binding.rvRecycler.apply {

            viewModel.readAllData.observe(this@MainActivity, Observer {
                var data = mutableListOf<Rows>()
                data = it as MutableList<Rows>


                adapter = NewsAdapter(data, this@MainActivity)
                layoutManager = manager

            })


        }

    }


}