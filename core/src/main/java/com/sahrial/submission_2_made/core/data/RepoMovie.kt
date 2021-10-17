package com.sahrial.submission_2_made.core.data


import com.sahrial.submission_2_made.core.data.source.local.LocalDataSource
import com.sahrial.submission_2_made.core.data.source.remote.RemoteDataSource
import com.sahrial.submission_2_made.core.data.source.remote.network.ResponseRetrofit
import com.sahrial.submission_2_made.core.data.source.remote.response.ResponseMovie
import com.sahrial.submission_2_made.core.domain.model.MovieModel
import com.sahrial.submission_2_made.core.domain.repository.IMovieRepository
import com.sahrial.submission_2_made.core.helper.AppExecutors
import com.sahrial.submission_2_made.core.helper.MapperData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepoMovie(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<MovieModel>>> =
        object : NetworkBoundResource<List<MovieModel>, List<ResponseMovie>>() {
            override fun loadFromDB(): Flow<List<MovieModel>> {
                return localDataSource.getAllMovie().map { MapperData.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<MovieModel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ResponseRetrofit<List<ResponseMovie>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<ResponseMovie>) {
                val movieList = MapperData.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<MovieModel>> {
        return localDataSource.getFavoriteMovie().map { MapperData.mapEntitiesToDomain(it) }

    }

    override fun setFavoriteMovie(movieModel: MovieModel, state: Boolean) {
        val movieEntity = MapperData.mapDomainToEntity(movieModel)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}

