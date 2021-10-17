package com.sahrial.submission_2_made.core.data.source.local

import com.sahrial.submission_2_made.core.data.source.local.entity.MovEntity
import com.sahrial.submission_2_made.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movList: List<MovEntity>) = movieDao.insertMovie(movList)

    fun setFavoriteMovie(mov: MovEntity, newState: Boolean) {
        mov.isFavorite = newState
        movieDao.updateFavoriteMovie(mov)
    }
}