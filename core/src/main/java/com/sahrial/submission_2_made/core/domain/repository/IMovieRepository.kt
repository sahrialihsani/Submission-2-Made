package com.sahrial.submission_2_made.core.domain.repository

import com.sahrial.submission_2_made.core.data.Resource
import com.sahrial.submission_2_made.core.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(): Flow<Resource<List<MovieModel>>>

    fun getFavoriteMovie(): Flow<List<MovieModel>>

    fun setFavoriteMovie(movieModel: MovieModel, state: Boolean)
}
