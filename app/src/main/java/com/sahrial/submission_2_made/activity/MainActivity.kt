package com.sahrial.submission_2_made.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.sahrial.submission_2_made.core.adapter.MovieAdapter
import com.sahrial.submission_2_made.core.data.Resource
import com.sahrial.submission_2_made.R
import com.sahrial.submission_2_made.activity.detail.DetailActivity
import com.sahrial.submission_2_made.databinding.ActivityMainBinding
import com.sahrial.submission_2_made.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EX_DATA, selectedData)
            startActivity(intent)
        }

        mainViewModel.movie.observe(this, { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            movie.message ?: getString(R.string.error)
                    }
                }
            }
        })

        val orientation = resources.configuration.orientation
        val count = if (orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4

        with(binding.rvMovie) {
            layoutManager = GridLayoutManager(context, count)
            setHasFixedSize(true)
            adapter = movieAdapter
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
                val uri = Uri.parse("submission_1_made://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun registerBroadCastReceiver() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    Intent.ACTION_POWER_CONNECTED -> {
                        Log.d("Connected", "Connected!")
                    }
                    Intent.ACTION_POWER_DISCONNECTED -> {
                        Log.d("Disconnected", "Disconnected!")
                    }
                }
            }
        }
        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onStart() {
        super.onStart()
        registerBroadCastReceiver()
    }
    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}