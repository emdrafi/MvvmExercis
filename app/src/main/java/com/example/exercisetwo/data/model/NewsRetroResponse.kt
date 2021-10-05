package com.example.exercisetwo.data.model

import com.google.gson.annotations.SerializedName

 data class NewsRetroResponse (
    @SerializedName("title") var title : String,
    @SerializedName("rows") var rows : List<Rows>
)