package com.example.exercisetwo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsModel(@SerializedName("title") @Expose val title:String? = null,
                     @SerializedName("description") @Expose  val description:String? = null,
                     @SerializedName("imageHref") @Expose val image:String? = null) {
}