package com.example.yourmovie.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.yourmovie.Model.Movie
import com.example.yourmovie.R

class MoviesAdapter(
    private var movies: List<Movie>,
    private val onMovieClick: (movie: Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun updateMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)
        private val titleMovie: TextView = itemView.findViewById(R.id.item_title_movie)
        private val dateMovieRelease: TextView = itemView.findViewById(R.id.item_release_date)

        fun bind(movie: Movie) {
            itemView.setOnClickListener { onMovieClick.invoke(movie) }
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(poster)
            titleMovie.setText(movie.title)
            dateMovieRelease.setText(movie.releaseDate)
        }
    }


}
