package com.sahrial.submission_2_made.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tb_movie")
data class MovEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "posterURL")
    var poster: String,

    @ColumnInfo(name = "backdropURL")
    var backdrop: String,

    @ColumnInfo(name = "releaseDate")
    var date: String,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
