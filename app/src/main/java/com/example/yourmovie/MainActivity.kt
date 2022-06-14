package com.example.yourmovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourmovie.Adapter.MoviesAdapter
import com.example.yourmovie.Controller.MoviesRepository
import com.example.yourmovie.Model.Movie

class MainActivity : AppCompatActivity() {
    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: MoviesAdapter
    private lateinit var topRatedMovies: RecyclerView
    private lateinit var topRatedMoviesAdapter: MoviesAdapter
    private var popularMoviesPage = 1
    private var topRatedMoviesPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popularMovies = findViewById(R.id.popular_movies)
        topRatedMovies = findViewById(R.id.top_rated_movies)

        popularMovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularMoviesAdapter = MoviesAdapter(listOf()) { movie -> showMovieDetails(movie) }
        popularMovies.adapter = popularMoviesAdapter


        topRatedMovies.layoutManager  = LinearLayoutManager(
        this,
        LinearLayoutManager.HORIZONTAL,
        false
        )

        topRatedMoviesAdapter = MoviesAdapter(listOf()) { movie -> showMovieDetails(movie) }
        topRatedMovies.adapter = topRatedMoviesAdapter

        getPopularMovies()
        getTopRatedMovies()

    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter.updateMovies(movies)
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }

    private fun getPopularMovies() {
        MoviesRepository.getPopularMovies(
            popularMoviesPage,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    private fun getTopRatedMovies() {
        MoviesRepository.getTopRatedMovies(
            topRatedMoviesPage,
            ::onTopRatedMoviesFetched,
            ::onError
        )
    }

    private fun onTopRatedMoviesFetched(movies: List<Movie>) {
        topRatedMoviesAdapter.updateMovies(movies)
    }

    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_BACKDROP, movie.backdropPath)
        intent.putExtra(MOVIE_POSTER, movie.posterPath)
        intent.putExtra(MOVIE_TITLE, movie.title)
        intent.putExtra(MOVIE_RATING, movie.rating)
        intent.putExtra(MOVIE_RELEASE_DATE, movie.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, movie.overview)
        startActivity(intent)
    }

}