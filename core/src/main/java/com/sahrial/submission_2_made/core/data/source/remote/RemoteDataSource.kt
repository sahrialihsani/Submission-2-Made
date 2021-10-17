package com.sahrial.submission_2_made.core.data.source.remote

import android.util.Log
import com.sahrial.submission_2_made.core.data.source.remote.network.ResponseRetrofit
import com.sahrial.submission_2_made.core.data.source.remote.network.ServiceRetrofit
import com.sahrial.submission_2_made.core.data.source.remote.response.ResponseMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val serviceRetrofit: ServiceRetrofit) {
    suspend fun getAllMovie(): Flow<ResponseRetrofit<List<ResponseMovie>>> {
        return flow {
            try {
                val response = serviceRetrofit.getPopularMovieList()
                val dataArray = response.movies
                if (dataArray.isNotEmpty()) {
                    emit(ResponseRetrofit.Success(response.movies))
                } else {
                    emit(ResponseRetrofit.Empty)
                }
            } catch (e: Exception) {
                emit(ResponseRetrofit.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

