package com.example.exercisetwo.ui.view.activity


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercisetwo.R
import com.example.exercisetwo.data.model.NewsModel
import com.example.exercisetwo.databinding.ActivityMainBinding
import com.example.exercisetwo.ui.adapter.NewsAdapter
import com.example.exercisetwo.ui.viewmodel.MainActivityViewModel

import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        manager=LinearLayoutManager(this)
        var data: ArrayList<NewsModel> = ArrayList()
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.getArrayList().observe(this, Observer {
            binding.rvRecycler.apply {
                try{
                    val obj= JSONObject(loadJSONFromAsset())
                    val rowsArray=obj.getJSONArray("rows")
                    for (i in 0 until rowsArray.length()) {
                        val rowsDetail = rowsArray.getJSONObject(i)
                        data.add(NewsModel(rowsDetail.getString("title"),rowsDetail.getString("description"),rowsDetail.getString("imageHref")))

                    }


                }catch (e: JSONException){
                    e.printStackTrace()
                }

                adapter=NewsAdapter(data,this@MainActivity)
                layoutManager=manager

            }
        })
    }
 fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream =assets.open("myJson.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }


}