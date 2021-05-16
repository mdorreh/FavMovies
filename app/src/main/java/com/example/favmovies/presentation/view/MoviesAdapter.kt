package com.example.favmovies.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Movie
import com.example.favmovies.databinding.ItemMovieLayoutBinding

class MoviesAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var movies: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMovieLayoutBinding =
            ItemMovieLayoutBinding.inflate(LayoutInflater.from(fragment.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun moviesList(list: List<Movie>) {
        movies = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movies[position]

        Glide.with(fragment)
            .load(movie.posterUrl)
            .into(holder.moviePoster)

        holder.movieTitle.text = movie.title

        holder.itemView.setOnClickListener {
            if(fragment is HomeFragment){
                fragment.movieDetails(movie.imdbId)
            }
        }
    }

    class ViewHolder(view: ItemMovieLayoutBinding) : RecyclerView.ViewHolder(view.root) {
        val moviePoster = view.moviePoster
        val movieTitle = view.movieTitle
    }
}

