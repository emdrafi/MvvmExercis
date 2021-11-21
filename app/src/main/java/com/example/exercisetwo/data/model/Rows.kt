package com.example.exercisetwo.data.model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "rows_table", indices = [Index(value = ["title","description"], unique = true)])
data class Rows(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")
    @SerializedName("title") val title: String,
    @ColumnInfo(name = "description")
    @SerializedName("description") val description: String,
    @ColumnInfo(name = "imageHref")
    @SerializedName("imageHref") val imageHref: String
) : Parcelable





