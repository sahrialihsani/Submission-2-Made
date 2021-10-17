package com.sahrial.submission_2_made.core.helper

import com.sahrial.submission_2_made.core.data.source.local.entity.MovEntity
import com.sahrial.submission_2_made.core.data.source.remote.response.ResponseMovie
import com.sahrial.submission_2_made.core.domain.model.MovieModel
import com.sahrial.submission_2_made.core.helper.Constants.Companion.IMAGE

object MapperData {
    fun mapResponsesToEntities(input: List<ResponseMovie>): List<MovEntity> {
        val movieList = ArrayList<MovEntity>()
        input.map {
            val movie = MovEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                poster = IMAGE + it.poster,
                backdrop = IMAGE + it.backdrop,
                date = it.date,
                voteAverage = it.voteAverage,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovEntity>): List<MovieModel> =
        input.map {
            MovieModel(
                id = it.id,
                title = it.title,
                overview = it.overview,
                poster = it.poster,
                backdrop = it.backdrop,
                date = it.date,
                voteAverage = it.voteAverage,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: MovieModel) = MovEntity(
        id = input.id,
        title = input.title,
        overview = input.overview,
        poster = input.poster,
        backdrop = input.backdrop,
        date = input.date,
        voteAverage = input.voteAverage,
        isFavorite = input.isFavorite
    )
}