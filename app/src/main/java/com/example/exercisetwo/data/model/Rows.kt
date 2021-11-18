package com.example.exercisetwo.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "rows_table")
data class Rows(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    @SerializedName("title") val title: String?,
    @ColumnInfo
    @SerializedName("description") val description: String?,
    @ColumnInfo
    @SerializedName("imageHref") val imageHref: String?
) : Parcelable





