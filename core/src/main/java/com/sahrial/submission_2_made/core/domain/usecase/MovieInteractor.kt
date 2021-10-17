package com.sahrial.submission_2_made.core.domain.usecase

import com.sahrial.submission_2_made.core.domain.model.MovieModel
import com.sahrial.submission_2_made.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movieModel: MovieModel, state: Boolean) =
        movieRepository.setFavoriteMovie(movieModel, state)
}