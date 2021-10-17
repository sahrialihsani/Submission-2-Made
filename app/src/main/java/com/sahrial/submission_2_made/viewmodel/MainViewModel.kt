package com.sahrial.submission_2_made.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sahrial.submission_2_made.core.domain.usecase.MovieUseCase

class MainViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}

