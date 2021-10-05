package com.example.exercisetwo.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exercisetwo.R
import com.example.exercisetwo.data.model.NewsModel
import com.example.exercisetwo.data.model.Rows
import com.example.exercisetwo.databinding.CardviewDesignBinding

import com.squareup.picasso.Picasso

class NewsAdapter(private val mList: List<Rows>, private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = CardviewDesignBinding.inflate(view, parent, false)

        return ViewHolder(binding)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])

    }

    override fun getItemCount(): Int {
        return mList.size
    }
    

    class ViewHolder(val binding: CardviewDesignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detils: Rows) {
            binding.user = detils

        }
    }

}