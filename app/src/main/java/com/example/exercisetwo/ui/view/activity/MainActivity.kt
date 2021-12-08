@file:Suppress("DEPRECATION")

package com.example.exercisetwo.ui.view.activity
import android.app.ProgressDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi

import androidx.lifecycle.ViewModelProvider
import com.example.exercisetwo.R
import com.example.exercisetwo.databinding.ActivityMainBinding
import com.example.exercisetwo.ui.view.fragments.ListFragment
import com.example.exercisetwo.ui.viewmodel.MainActivityViewModel
import com.example.exercisetwo.util.NetworkHelper

@Suppress("DEPRECATION", "DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        getListFragment()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getListFragment() {
        if(NetworkHelper.isNetworkConnected(this)){
            val progressDialog=ProgressDialog(this)
            progressDialog.setTitle("Loading")
            progressDialog.show()
            viewModel.getNewsDatas()?.observe(this, {
                viewModel.addRows(it.rows)
                progressDialog.cancel()
            })
            val fragment=ListFragment()
            showFragment(fragment)
        }else{
            Toast.makeText(applicationContext,getString(R.string.msg_netconnection),Toast.LENGTH_SHORT).show()
        }
    }
   private fun showFragment(fragment: ListFragment){
        val fram = supportFragmentManager.beginTransaction()
        fram.replace(R.id.fragment_main,fragment)
        fram.commit()
    }


}