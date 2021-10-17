package com.sahrial.submission_2_made.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val id: String,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val date: String,
    val voteAverage: String,
    val isFavorite: Boolean
) : Parcelable