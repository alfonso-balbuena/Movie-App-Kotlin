package com.alfonso.myapplication.showlist.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alfonso.myapplication.databinding.MovieItemBinding
import com.alfonso.myapplication.repository.model.Movie
import com.bumptech.glide.Glide

class MovieAdapter(private val clickListener: MovieClickListener) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

    class MovieViewHolder private constructor(private val binding : MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie : Movie,clickListener: MovieClickListener) {
            binding.parentItem.setOnClickListener { clickListener.onClick(movie) }
            binding.title.text = movie.title
            binding.voteAverage.text = movie.vote_average.toString()
            Glide.with(binding.root)
                .load(movie.poster_path)
                .fitCenter()
                .into(binding.poster)
        }

        companion object {
            fun create(parent: ViewGroup) : MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieItemBinding.inflate(layoutInflater,parent,false)
                return MovieViewHolder(binding)
            }
        }
    }

}

class MovieClickListener(val listener: (movie : Movie) -> Unit) {
    fun onClick(movie: Movie)  = listener(movie)
}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}