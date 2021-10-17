package com.sahrial.submission_2_made.di

import com.sahrial.submission_2_made.core.domain.usecase.MovieInteractor
import com.sahrial.submission_2_made.core.domain.usecase.MovieUseCase
import com.sahrial.submission_2_made.viewmodel.DetailMovieViewModel
import com.sahrial.submission_2_made.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}