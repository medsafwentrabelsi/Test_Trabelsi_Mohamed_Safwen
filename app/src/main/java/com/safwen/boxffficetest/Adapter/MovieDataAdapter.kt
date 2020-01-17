package com.safwen.boxffficetest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.safwen.boxffficetest.R

import com.safwen.boxffficetest.data.entities.MovieObject
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieDataAdapter(
    var movieList: ArrayList<MovieObject>,
    val itemClickListener: OnItemClickListener
) :

    RecyclerView.Adapter<MovieDataAdapter.CommitViewHolder>() {
    fun updateMovieList(newMovieList: List<MovieObject>) {
        movieList.clear()
        if (movieList != null) {
            movieList.addAll(newMovieList)
        }
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = CommitViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
    )


    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        holder.bind(movieList[position],itemClickListener)
        (movieList[position])

    }

    class CommitViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        private val movieNameTextView = view.movie_name_text_view_movie_item
        private val movieYearTextView = view.year_text_view_movie_item
        private val moviePoster = view.poster_image_view_movie_item


        private val rootView = view

        fun bind(movie: MovieObject, clickListener: OnItemClickListener) {


            movieNameTextView.text = movie.movieTitle
            movieYearTextView.text = movie.movieYear
            val options = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.mipmap.ic_launcher_round)
                .circleCrop()

            Glide.with(rootView)
                .setDefaultRequestOptions(options)
                .load(movie.moviePoster)
                .into(moviePoster)
            itemView.setOnClickListener { clickListener.onItemClicked(movie) }


        }

    }
    interface OnItemClickListener{
        fun onItemClicked(movie: MovieObject)
    }


}
