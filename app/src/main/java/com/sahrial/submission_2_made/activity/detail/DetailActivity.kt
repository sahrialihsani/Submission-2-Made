package com.sahrial.submission_2_made.activity.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sahrial.submission_2_made.core.domain.model.MovieModel
import com.sahrial.submission_2_made.R
import com.sahrial.submission_2_made.databinding.ActivityDetailBinding
import com.sahrial.submission_2_made.viewmodel.DetailMovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EX_DATA = "ex_data"
    }

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<MovieModel>(EX_DATA)
        populateMovie(movie)
    }

    private fun populateMovie(movieModel: MovieModel?) {
        movieModel?.let {
            title = movieModel.title

            binding.tvDetailTitle.text = movieModel.title
            (movieModel.date).also {
                binding.tvDetailDate.text = it
            }
            (movieModel.voteAverage).also {
                binding.tvDetailRate.text = it
            }
            binding.tvOverview.text = movieModel.overview
            Glide.with(this)
                .load(movieModel.poster)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.ivPosterImage)
            Glide.with(this)
                .load(movieModel.backdrop)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.ivBackdrop)

            var status = movieModel.isFavorite
            setFavorite(status)
            binding.fabFav.setOnClickListener {
                status = !status
                detailMovieViewModel.setFavoriteMovie(movieModel, status)
                setFavorite(status)
            }
        }
    }

    private fun setFavorite(status: Boolean) {
        if (status) {
            binding.fabFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.fabFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                val uri = Uri.parse("submission_2_made://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}