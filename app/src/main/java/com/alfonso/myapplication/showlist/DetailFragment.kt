package com.alfonso.myapplication.showlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.alfonso.myapplication.R
import com.alfonso.myapplication.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        val movie = args.movie
        binding.language.text = movie.original_language
        binding.overview.text = movie.overview
        binding.rate.text = movie.vote_average.toString()
        binding.title.text = movie.title
        binding.release.text = movie.release_date
        Glide.with(binding.root)
            .load(movie.poster_path)
            .fitCenter()
            .into(binding.poster)
        return binding.root
    }

}