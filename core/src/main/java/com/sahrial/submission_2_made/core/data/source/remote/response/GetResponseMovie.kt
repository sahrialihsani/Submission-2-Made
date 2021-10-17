package com.sahrial.submission_2_made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GetResponseMovie(
    @field:SerializedName("page")
    val page: String,

    @field:SerializedName("total_pages")
    val totalPages: String,

    @field:SerializedName("total_results")
    val totalResults: String,

    @field:SerializedName("results")
    val movies: List<ResponseMovie>
)