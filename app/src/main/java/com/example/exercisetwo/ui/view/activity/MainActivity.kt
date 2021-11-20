package com.example.exercisetwo.ui.view.activity
import android.app.ProgressDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercisetwo.R
import com.example.exercisetwo.databinding.ActivityMainBinding
import com.example.exercisetwo.ui.view.fragments.ListFragment
import com.example.exercisetwo.ui.viewmodel.MainActivityViewModel
import com.example.exercisetwo.util.NetworkHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var viewModel: MainActivityViewModel

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        manager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        getFragment()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getFragment() {
        if(NetworkHelper.isNetworkConnected(this)){
            val progressDialog=ProgressDialog(this)
            progressDialog.setTitle("Loading")
            progressDialog.show()
            viewModel.getUser()?.observe(this, Observer {

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