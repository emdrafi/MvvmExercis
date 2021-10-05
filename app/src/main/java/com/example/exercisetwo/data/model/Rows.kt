package com.example.exercisetwo.data.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso

data class Rows(
    @SerializedName("title") var title : String,
    @SerializedName("description") var description : String,
    @SerializedName("imageHref") var imageHref : String


)




