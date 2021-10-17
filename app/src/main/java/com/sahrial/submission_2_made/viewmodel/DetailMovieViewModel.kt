package com.sahrial.submission_2_made.viewmodel

import androidx.lifecycle.ViewModel
import com.sahrial.submission_2_made.core.domain.model.MovieModel
import com.sahrial.submission_2_made.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movieModel: MovieModel, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movieModel, newStatus)
}

