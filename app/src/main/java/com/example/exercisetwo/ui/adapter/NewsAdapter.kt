package com.example.exercisetwo.ui.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercisetwo.R
import com.example.exercisetwo.data.model.Rows


class NewsAdapter(private val mList: List<Rows>, private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        val url: String? = itemsViewModel.imageHref?.replace("http","https")
       Glide.with(context).load(url).into(holder.imageView)
        holder.tvTitle.text = itemsViewModel.title
        holder.tvDescription.text=itemsViewModel.description

    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_news)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvDescription: TextView=itemView.findViewById(R.id.tv_description)
    }
}

