package com.example.exercisetwo.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercisetwo.R
import com.example.exercisetwo.data.model.Rows
import com.example.exercisetwo.ui.adapter.NewsAdapter
import com.example.exercisetwo.ui.viewmodel.MainActivityViewModel

class ListFragment:Fragment() {
      lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.readAllData.observe(viewLifecycleOwner, {

            val data = it as MutableList<Rows>
           val adapter = NewsAdapter(data, requireContext())
            val recyclerView = view.findViewById<RecyclerView>(R.id.rv_recycler)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

        })

        return view
    }
}