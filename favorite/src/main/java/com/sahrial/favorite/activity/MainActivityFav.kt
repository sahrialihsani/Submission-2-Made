package com.sahrial.favorite.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahrial.favorite.adapter.FavoriteAdapter
import com.sahrial.favorite.databinding.ActivityMainFavBinding
import com.sahrial.favorite.di.favoriteModule
import com.sahrial.favorite.viewmodel.FavoriteViewModel
import com.sahrial.submission_2_made.activity.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MainActivityFav : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private lateinit var binding: ActivityMainFavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainFavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val favoriteMovieAdapter = FavoriteAdapter()
        favoriteMovieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EX_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovie.observe(this, { dataMovie ->
            favoriteMovieAdapter.setData(dataMovie)
        })

        with(binding.rvFav) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteMovieAdapter
        }

        title = "My Favorite"
    }
}