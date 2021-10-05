package com.example.exercisetwo.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.exercisetwo.R
import com.example.exercisetwo.data.model.NewsModel
import com.example.exercisetwo.data.model.Rows
import com.example.exercisetwo.databinding.CardviewDesignBinding

import com.squareup.picasso.Picasso

class NewsAdapter(private val mList:List<Rows>, private val context:Context):
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = CardviewDesignBinding.inflate(view,parent,false)
//            .inflate(R.layout.cardview_design, parent, false)

        return ViewHolder(binding)
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
//        val ItemsViewModel = mList[position]
//        holder.binding.tvTitle.text=ItemsViewModel.title
//        holder.binding.tvDescription.text=ItemsViewModel.description
//        holder.binding.imgNews.setImageURI()
//        holder.tvTitle.text=ItemsViewModel.title
//        holder.tvDesc.text=ItemsViewModel.description
//        if (ItemsViewModel.image !== null) {
//
//            Picasso.get().load(ItemsViewModel.image).error(context.resources.getDrawable(R.drawable.ic_launcher_background)).into(holder.binding.imgNews)
//        }else{
//            holder.binding.imgNews.setImageResource(R.drawable.ic_launcher_background)
//
//        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(val binding: CardviewDesignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detils:Rows){
            binding.user=detils

        }
//        val tvTitle:TextView=itemView.findViewById(R.id.tv_title)
//        val tvDesc:TextView=itemView.findViewById(R.id.tv_description)
//        val imageNews:ImageView=itemView.findViewById(R.id.img_news)
    }

}