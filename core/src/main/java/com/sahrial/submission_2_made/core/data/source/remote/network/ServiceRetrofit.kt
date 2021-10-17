package com.sahrial.submission_2_made.core.data.source.remote.network

import com.sahrial.submission_2_made.core.data.source.remote.response.GetResponseMovie
import retrofit2.http.GET

interface ServiceRetrofit {
    @GET("movie/popular?api_key=c653cb39d2ddb30afc15b4e42d757573")
    suspend fun getPopularMovieList(): GetResponseMovie
}
