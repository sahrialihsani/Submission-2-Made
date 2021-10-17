package com.sahrial.submission_2_made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
data class ResponseMovie(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("backdrop_path")
    val backdrop: String,

    @field:SerializedName("release_date")
    val date: String,

    @field:SerializedName("vote_average")
    val voteAverage: String,
)

